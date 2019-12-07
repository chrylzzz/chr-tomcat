package com.chryl.tomcat.server;

import com.chryl.tomcat.config.ChrServletConfig;
import com.chryl.tomcat.config.ChrServletMappingConfig;
import com.chryl.tomcat.servlet.ChrRequest;
import com.chryl.tomcat.servlet.ChrResponse;
import com.chryl.tomcat.utils.HttpUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tomcat Start
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class ChrTomcat {

    //端口
    private int port = 8080;

    public ChrTomcat() throws ClassNotFoundException {
        initServlet();

    }

    public ChrTomcat(int port) throws ClassNotFoundException {
        this.port = port;
        initServlet();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    //key:urlMapping , value:反射得到的chrServlet类
    private Map<String, Class<ChrServlet>> stringClassMap = new HashMap<>();

    //初始化servlet,模拟扫描servlet
    public void initServlet() throws ClassNotFoundException {
        //获得所有的继承chrServlet的servlet信息,类似于自动扫描的功能
        List<ChrServletConfig> chrServletConfigs = ChrServletMappingConfig.getChrServletConfigs();
        for (ChrServletConfig chrServletConfig : chrServletConfigs) {
            stringClassMap.put(chrServletConfig.getUrlMapping(), (Class<ChrServlet>) Class.forName(chrServletConfig.getClazz()));

        }
    }

    //分发请求
    public void dispatch(ChrRequest chrRequest, ChrResponse chrResponse) throws IllegalAccessException, InstantiationException {
        Class<ChrServlet> chrServletClass = stringClassMap.get(chrRequest.getUrl());
        if (chrServletClass != null) {
            ChrServlet chrServlet = chrServletClass.newInstance();
            chrServlet.service(chrRequest, chrResponse);
        } else {
            //未找到,返回404
            chrResponse.write(HttpUtil.getHttpResponseContext(404, "Not Found", "error"));
        }
    }

    //入口
    public void start() throws IOException, InstantiationException, IllegalAccessException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ChrTomcat started on " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            //需要request(请求头信息),网络信息分段,有可能为0,需要保证网络信息的完整性

            ChrRequest chrRequest = new ChrRequest(socket.getInputStream());
            ChrResponse chrResponse = new ChrResponse(socket.getOutputStream());
//            chrResponse.writeHtml(chrRequest.getUrl());
            if (chrRequest.getUrl().equals("/")) {//未找到页面
                chrResponse.write(HttpUtil.getHttpResponseContext(404, "Not Found", ""));
            } else if ((stringClassMap.get(chrRequest.getUrl()) == null)) {//默认欢迎页面,可以自定义
                chrResponse.writeHtml(chrRequest.getUrl());
            } else {//相应的业务页面
                dispatch(chrRequest, chrResponse);
            }

            socket.close();
        }
    }


    public static void main(String[] args) throws Exception {
        ChrTomcat chrTomcat = new ChrTomcat();

        chrTomcat.start();
    }
}
