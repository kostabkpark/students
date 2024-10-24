package com.example.student.ch07;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.SessionListener;

@Slf4j
@WebListener
public class ListenerExam implements
        ServletContextListener,
        ServletContextAttributeListener,
        HttpSessionListener,
        HttpSessionAttributeListener
{
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        log.info("contextAttributeAdded ********: {} ",  scae);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized ********: {} ",  sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed ********: {} ",  sce);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        log.info("sessionAttributeAdded ********: {} ",  se);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("sessionCreated ********: {} ",  se);
    }
}
