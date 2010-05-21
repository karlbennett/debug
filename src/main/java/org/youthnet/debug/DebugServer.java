package org.youthnet.debug;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: karl
 * Date: 18-May-2010
 */
public class DebugServer {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("config/spring/server-config.xml");
        context.registerShutdownHook();
    }
}
