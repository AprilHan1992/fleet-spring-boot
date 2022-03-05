package com.fleet.shiro.online.config.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class OnlineListener implements SessionListener {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        atomicInteger.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        atomicInteger.decrementAndGet();
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public int get() {
        return atomicInteger.get();
    }
}
