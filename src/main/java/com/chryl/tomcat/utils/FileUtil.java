package com.chryl.tomcat.utils;

import java.io.*;

/**
 * 不完整
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class FileUtil {
    public static boolean writeFile(InputStream inputStream, OutputStream outputStream) {
        boolean success = false;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(HttpUtil.getHttpResponseContext(200, "", "").getBytes());
            int count = 0;
            while (count == 0) {//没有信息就一直卡住
                count = inputStream.available();//有数据,预估网络信息的大小
            }

            int fileSize = inputStream.available();
            long written = 0;
            int byteSize = 1024;
            byte[] bytes = new byte[byteSize];
            while (written < fileSize) {
                if (written + byteSize > fileSize) {
                    byteSize = (int) (fileSize - written);
                    bytes = new byte[byteSize];
                }
                bufferedInputStream.read(bytes);
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                written += byteSize;
            }
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean writeFile(File file, OutputStream outputStream) throws FileNotFoundException {
        return writeFile(new FileInputStream(file), outputStream);
    }

    public static String getResourcePath(String path) {
        String resource = FileUtil.class.getResource("/").getPath();
        return resource + path;
    }
}
