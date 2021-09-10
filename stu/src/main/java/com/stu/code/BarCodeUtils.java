package com.stu.code;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author leihaoyuan
 * @date 2020/05/22 15:05
 * @description 条形码生成工具类
 */
public class BarCodeUtils {

    private static final String MIME_TYPE_FORMAT = "image/png";

    /**
     * 生成文件
     *
     * @param content
     * @param path
     * @return
     */
    public static File generateFile(String content, String path) {
        File file = new File(path);
        try {
            generate(content, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param content
     * @return
     */
    public static byte[] generate(String content) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(content, ous);
        return ous.toByteArray();
    }

    /**
     * 生成到流
     *
     * @param content
     * @param ous
     */
    public static void generate(String content, OutputStream ous) {
        if (StringUtils.isEmpty(content) || ous == null) {
            return;
        }
        Code39Bean bean = new Code39Bean();
        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);
        try {
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, MIME_TYPE_FORMAT, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            // 生成条形码
            bean.generateBarcode(canvas, content);
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        generateFile("123456789", "C:\\工作文档\\000Docker\\二维码测试\\testbar.jpg");
    }
}