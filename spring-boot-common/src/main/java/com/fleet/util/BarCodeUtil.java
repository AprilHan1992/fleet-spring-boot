package com.fleet.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 条形码工具类
 */
public class BarCodeUtil {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT_NAME = "JPG";

    // 条形码宽度
    public static int BARCODE_WIDTH = 300;

    // 条形码高度
    public static int BARCODE_HEIGHT = 70;

    /**
     * 生成条形码
     *
     * @param contents 内容（只能用数字、字母、符号）
     * @param destPath 目标路径
     * @throws Exception
     */
    public static void encode(String contents, String destPath, String fileName) throws Exception {
        mkdirs(destPath);
        BufferedImage bufferedImage = BarCodeUtil.bufferedImage(contents);
        File dest = new File(destPath + "/" + fileName);
        if (dest.exists() && dest.isFile()) {
            dest.delete();
        }
        ImageIO.write(bufferedImage, FORMAT_NAME, dest);
    }

    /**
     * 生成条形码
     *
     * @param contents 内容（只能用数字、字母、符号）
     * @param os       输出流
     * @throws Exception
     */
    public static void encode(String contents, OutputStream os) throws Exception {
        BufferedImage bufferedImage = BarCodeUtil.bufferedImage(contents);
        ImageIO.write(bufferedImage, FORMAT_NAME, os);
    }

    /**
     * 解析条形码
     *
     * @param file 条形码图片
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage bufferedImage = ImageIO.read(file);
        if (bufferedImage == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        Result result = new MultiFormatReader().decode(bitmap, hints);
        if (result == null) {
            return null;
        }
        return result.getText();
    }

    /**
     * 解析条形码
     *
     * @param path 条形码图片地址
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return BarCodeUtil.decode(new File(path));
    }

    /**
     * 创建目录
     *
     * @param destPath 目标路径
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    private static BufferedImage bufferedImage(String contents) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 10);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, BARCODE_WIDTH, BARCODE_HEIGHT, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
