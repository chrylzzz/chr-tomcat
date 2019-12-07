package com.chryl.tomcat.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 映射类,得到继承chrServlet的servlet的配置信息
 * <p>
 * Created by Chryl on 2019/12/3.
 */
public class ChrServletMappingConfig {

    //
    private static List<ChrServletConfig> chrServletConfigs = new ArrayList<>();

    static {
        //模拟web.xml 配置信息
        chrServletConfigs.add(
                /**
                 * 1.继承chrServlet的servletClassName
                 * 2.页面访问的url
                 * 3.继承chrServlet的servletClass反射到那个类全路径
                 */
                new ChrServletConfig("TestServlet", "/chr", "com.chryl.tomcat.test.TestSetvlet")
        );
    }

    public static List<ChrServletConfig> getChrServletConfigs() {
        return chrServletConfigs;
    }

}
