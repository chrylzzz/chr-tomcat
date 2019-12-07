package com.chryl.tomcat.utils;

/**
 * 不完整
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class HttpUtil {
    public static final String N = "\n";
    public static final String RN = "\r\n";

    public static String getHttpResponseContext(int code, String content, String errorMsg) {
        if (code == 200) {
            return "HTTP/1.1 200 OK" + N + "Content-Type:text/html" + N + RN + content;
        } else if (code == 500) {
            return "HTTP/1.1 500 Internal Error" + errorMsg + N + "Content-Type:text/html" + N + RN;


        }

        return "HTTP/1.1 404 Not Found" + N + "Content-Type:text/html" + N + RN;


    }
}
