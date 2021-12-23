package com.example.practice_pro.sunCode.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName ImgUtils
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/9 14:11
 * @Version 1.0
 */
public class ImgUtils {
    private static final String SAVE_PATH = "C:\\Users\\hongguo.zhu\\Desktop\\R-C.jpg";

    public void getFile(HttpServletRequest request, HttpServletResponse response) {
        //读取路径下面的文件
        File imgFile = new File(SAVE_PATH);
        //获取文件后缀名格式
        String ext = imgFile.getName().substring(imgFile.getName().indexOf("."));
        //判断图片格式,设置相应的输出文件格式
        if (ext.equals("jpg")) {
            response.setContentType("image/jpeg");
        } else if (ext.equals("JPG")) {
            response.setContentType("image/jpeg");
        }
        InputStream in = null;
        OutputStream outputStream = null;
        try {
            //读取指定路径下面的文件
            in = new FileInputStream(imgFile);
            outputStream = new BufferedOutputStream(response.getOutputStream());
            //创建存放文件内容的数组
            byte[] buff = new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
