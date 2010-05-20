package org.youthnet.debug;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: karl
 * Date: 18-May-2010
 */
public class DebugServer {
    private static AbstractApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("config/spring/server-config.xml");
    }
}
