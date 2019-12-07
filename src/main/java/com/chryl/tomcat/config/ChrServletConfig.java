package com.chryl.tomcat.config;

/**
 * 配置类(处理继承chrServlet的类信息)代替web.xml:</url-mapping>,clazz,请求路径
 * tomcat需要找到配置的ChrServlet:才能找到servlet类
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class ChrServletConfig {

    //访问路径
    private String name;
    //映射路径
    private String urlMapping;
    //包路径
    private String clazz;

    public ChrServletConfig(String name, String urlMapping, String clazz) {
        this.name = name;
        this.urlMapping = urlMapping;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlMapping() {
        return urlMapping;
    }

    public void setUrlMapping(String urlMapping) {
        this.urlMapping = urlMapping;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }


}
