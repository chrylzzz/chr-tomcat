package com.chryl.tomcat.servlet;

import com.chryl.tomcat.utils.FileUtil;
import com.chryl.tomcat.utils.HttpUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 封装响应头
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class ChrResponse {

    private OutputStream outputStream;

    public ChrResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) {
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    public void writeHtml(String path) throws FileNotFoundException {
        //读取静态资源
        String resourcePath = FileUtil.getResourcePath(path);
        File file = new File(resourcePath);
        if (file.exists()) {
            FileUtil.writeFile(file, outputStream);
        } else {
            //为找到
            write(HttpUtil.getHttpResponseContext(404, "404 未找到", "Not Found"));
        }

    }
}
