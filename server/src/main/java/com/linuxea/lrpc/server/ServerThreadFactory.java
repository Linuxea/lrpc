package com.linuxea.lrpc.server;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThreadFactory implements ThreadFactory {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "lrpc-server-thread:" + counter.getAndAdd(1));
        thread.setDaemon(true);
        return thread;
    }
}
