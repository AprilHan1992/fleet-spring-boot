package com.fleet.online.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        online++;
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        online--;
    }
}
