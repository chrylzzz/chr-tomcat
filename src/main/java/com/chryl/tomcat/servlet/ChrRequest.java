package com.chryl.tomcat.servlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装请求头:只封装必须的内容:
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class ChrRequest {
    //url,请求方法,io
    private String url;
    private String method;
    private InputStream inputStream;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ChrRequest(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        int count = 0;
        while (count == 0) {//没有信息就一直卡住
            count = inputStream.available();//有数据,预估网络信息的大小
        }
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);

        //处理,解析
        extractFileds(new String(bytes));

    }

    //
    private void extractFileds(String content) {
        if (content.equals("")) {
            System.out.println("content empty...");
        } else {
            String firstLine = content.split("\\n")[0];//根据第一个换行符号切割,作为第一段
            String[] contents = firstLine.split("\\s");//根据空格符
            setUrl(contents[1]);
            setMethod(contents[0]);
        }
    }


}
