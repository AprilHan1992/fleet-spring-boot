package com.fleet.webmagic.pipeline;

import com.fleet.webmagic.entity.Content;
import com.fleet.webmagic.repository.ContentRepository;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author April Han
 */
@Service
public class ContentPipeline implements Pipeline {

    @Resource
    private ContentRepository contentRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (String key : resultItems.getAll().keySet()) {
            if ("contentList".equals(key)) {
                List<Content> contentList = (List<Content>) resultItems.getAll().get("contentList");
                if (contentList != null) {
                    contentRepository.saveAll(contentList);
                }
            }
        }
    }
}
