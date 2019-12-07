package com.chryl.tomcat.test;

import com.chryl.tomcat.server.ChrServlet;
import com.chryl.tomcat.servlet.ChrRequest;
import com.chryl.tomcat.servlet.ChrResponse;
import com.chryl.tomcat.utils.HttpUtil;

/**
 * Created by Chryl on 2019/12/3.
 */
public class TestSetvlet extends ChrServlet {
    @Override
    public void doGet(ChrRequest chrRequest, ChrResponse chrResponse) {
        String content = "<h1>" + "my do Get" + "</h1>";
        chrResponse.write(HttpUtil.getHttpResponseContext(200, content, "OK"));
    }

    @Override
    public void doPost(ChrRequest chrRequest, ChrResponse chrResponse) {
        String content = "<h1>" + "my do Post" + "</h1>";
        chrResponse.write(HttpUtil.getHttpResponseContext(200, content, "OK"));
    }
}
