package com.fleet.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * 二维码工具类
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT_NAME = "JPG";

    // 二维码宽度
    public static int QRCODE_WIDTH = 300;

    // 二维码高度
    public static int QRCODE_HEIGHT = 300;

    // logo宽度
    public static int LOGO_WIDTH = 60;

    // logo高度
    public static int LOGO_HEIGHT = 60;

    /**
     * 生成二维码
     *
     * @param contents 内容
     * @param destPath 目标路径
     * @throws Exception
     */
    public static void encode(String contents, String destPath, String fileName) throws Exception {
        mkdirs(destPath);
        BufferedImage bufferedImage = QRCodeUtil.bufferedImage(contents);
        File dest = new File(destPath + "/" + fileName);
        if (dest.exists() && dest.isFile()) {
            dest.delete();
        }
        ImageIO.write(bufferedImage, FORMAT_NAME, dest);
    }

    /**
     * 生成二维码(内嵌logo)
     *
     * @param contents  内容
     * @param logoImage logo图片
     * @param destPath  目标路径
     * @throws Exception
     */
    public static void encode(String contents, String destPath, String fileName, File logoImage) throws Exception {
        mkdirs(destPath);
        BufferedImage bufferedImage = QRCodeUtil.bufferedImage(contents);
        QRCodeUtil.insertLogoImage(bufferedImage, logoImage);
        File dest = new File(destPath + "/" + fileName);
        if (dest.exists() && dest.isFile()) {
            dest.delete();
        }
        ImageIO.write(bufferedImage, FORMAT_NAME, dest);
    }

    /**
     * 生成二维码
     *
     * @param contents 内容
     * @param os       输出流
     * @throws Exception
     */
    public static void encode(String contents, OutputStream os) throws Exception {
        BufferedImage bufferedImage = QRCodeUtil.bufferedImage(contents);
        ImageIO.write(bufferedImage, FORMAT_NAME, os);
    }

    /**
     * 生成二维码(内嵌logo)
     *
     * @param contents  内容
     * @param logoImage logo图片
     * @param os        输出流
     * @throws Exception
     */
    public static void encode(String contents, File logoImage, OutputStream os) throws Exception {
        BufferedImage bufferedImage = QRCodeUtil.bufferedImage(contents);
        QRCodeUtil.insertLogoImage(bufferedImage, logoImage);
        ImageIO.write(bufferedImage, FORMAT_NAME, os);
    }

    /**
     * 解析二维码
     *
     * @param file 二维码图片
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
     * 解析二维码
     *
     * @param path 二维码图片地址
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
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
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return bufferedImage;
    }

    /**
     * 插入logo
     *
     * @param bufferedImage 二维码图片
     * @param logoImage     logo图片
     * @throws Exception
     */
    private static void insertLogoImage(BufferedImage bufferedImage, File logoImage) throws Exception {
        if (!logoImage.exists()) {
            throw new Exception("logo文件不存在");
        }

        Image srcLogoImage = ImageIO.read(logoImage);
        Image zoomedLogoImage = srcLogoImage.getScaledInstance(LOGO_WIDTH, LOGO_HEIGHT, Image.SCALE_SMOOTH);
        Graphics graphics = new BufferedImage(LOGO_WIDTH, LOGO_HEIGHT, BufferedImage.TYPE_INT_RGB).getGraphics();
        graphics.drawImage(zoomedLogoImage, 0, 0, null); // 绘制缩小后的图
        graphics.dispose();

        // 插入logo
        Graphics2D graphics2D = bufferedImage.createGraphics();
        int x = (QRCODE_WIDTH - LOGO_WIDTH) / 2;
        int y = (QRCODE_HEIGHT - LOGO_HEIGHT) / 2;
        graphics2D.drawImage(zoomedLogoImage, x, y, LOGO_WIDTH, LOGO_HEIGHT, null);
        Shape shape = new RoundRectangle2D.Float(x, y, LOGO_WIDTH, LOGO_HEIGHT, 6, 6);
        graphics2D.setStroke(new BasicStroke(3f));
        graphics2D.draw(shape);
        graphics2D.dispose();
    }
}
