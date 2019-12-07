package com.chryl.tomcat.server;

import com.chryl.tomcat.servlet.ChrRequest;
import com.chryl.tomcat.servlet.ChrResponse;

/**
 * 核心servlet
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public abstract class ChrServlet {

    public abstract void doGet(ChrRequest chrRequest, ChrResponse chrResponse);

    public abstract void doPost(ChrRequest chrRequest, ChrResponse chrResponse);

    //
    public void service(ChrRequest chrRequest, ChrResponse chrResponse) {
        if ("GET".equals(chrRequest.getMethod())) {
            doGet(chrRequest, chrResponse);
        } else {
            doPost(chrRequest, chrResponse);
        }

    }

}
