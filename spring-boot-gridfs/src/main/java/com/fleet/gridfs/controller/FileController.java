package com.fleet.gridfs.controller;

import com.fleet.gridfs.entity.FileInfo;
import com.fleet.gridfs.util.UUIDUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final String COLLECTION_NAME = "files";

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private GridFsTemplate gridFsTemplate;

    @Resource
    private GridFSBucket gridFsBucket;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public FileInfo upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        // 判断文件是否存在
        FileInfo fileInfo = getByMd5(md5);
        if (fileInfo != null) {
            return fileInfo;
        }

        fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setMd5(md5);
        fileInfo.setContentType(file.getContentType());
        fileInfo.setGridfsId(store(file.getInputStream(), file.getContentType()));
        fileInfo.setDate(new Date());
        fileInfo = mongoTemplate.save(fileInfo, COLLECTION_NAME);
        return fileInfo;
    }

    /**
     * 根据 md5 获取文件对象
     */
    public FileInfo getByMd5(String md5) {
        Query query = new Query().addCriteria(Criteria.where("md5").is(md5));
        return mongoTemplate.findOne(query, FileInfo.class, COLLECTION_NAME);
    }

    /**
     * 存储文件到 Mongodb 的 GridFs 中
     */
    private String store(InputStream is, String contentType) {
        String gridfsId = UUIDUtil.getUUID();
        gridFsTemplate.store(is, gridfsId, contentType);
        return gridfsId;
    }

    /**
     * 下载文件
     */
    @RequestMapping("/download/{id}")
    public ResponseEntity<Object> download(@PathVariable("id") String id) throws IOException {
        FileInfo fileInfo = mongoTemplate.findById(id, FileInfo.class, COLLECTION_NAME);
        if (fileInfo != null) {
            Query query = new Query().addCriteria(Criteria.where("filename").is(fileInfo.getGridfsId()));
            GridFSFile gridFsFile = gridFsTemplate.findOne(query);
            if (gridFsFile == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }
            GridFSDownloadStream gds = gridFsBucket.openDownloadStream(gridFsFile.getObjectId());
            if (gds.getGridFSFile().getLength() == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }
            GridFsResource gridFsResource = new GridFsResource(gridFsFile, gds);
            InputStream is = gridFsResource.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) > 0) {
                baos.write(b, 0, len);
            }
            byte[] bytes = baos.toByteArray();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + new String(fileInfo.getName().getBytes(), StandardCharsets.ISO_8859_1))
                    .header(HttpHeaders.CONTENT_LENGTH, fileInfo.getSize() + "")
                    .header("Connection", "close")
                    .body(bytes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    /**
     * 删除文件
     */
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        FileInfo fileInfo = mongoTemplate.findById(id, FileInfo.class, COLLECTION_NAME);
        if (fileInfo != null) {
            Query query = new Query().addCriteria(Criteria.where("_id").is(id));
            mongoTemplate.remove(query, COLLECTION_NAME);

            Query deleteQuery = new Query().addCriteria(Criteria.where("filename").is(fileInfo.getGridfsId()));
            gridFsTemplate.delete(deleteQuery);
        }
    }

    /**
     * 文件列表
     */
    @RequestMapping("/list")
    public List<FileInfo> list(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageRows") int pageRows) {
        Query query = new Query().with(new Sort(Sort.Direction.DESC, "date"));
        query.skip((pageIndex - 1) * pageRows);
        query.limit(pageRows);
        return mongoTemplate.find(query, FileInfo.class, COLLECTION_NAME);
    }
}
