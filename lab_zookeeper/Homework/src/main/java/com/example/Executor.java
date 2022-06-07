package com.example;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import java.io.IOException;
import org.apache.zookeeper.ClientCnxn;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.LoggerFactory;

public class Executor implements Runnable, Watcher {

    private static final String HOST_PORT = "127.0.0.1:2191";

    private static final int SESSION_TIMEOUT = 3000;

    private final ZWatcher zWatcher;

    private boolean isRunning = true;

    public Executor() throws IOException {
        final ZooKeeper zooKeeper = new ZooKeeper(HOST_PORT, SESSION_TIMEOUT, this);
        zWatcher = new ZWatcher(zooKeeper);
        changeClientCnxnLogging();
    }

    @Override
    public void process(WatchedEvent event) {
        zWatcher.process(event);
    }

    public static void main(String[] args) throws IOException {
        new Executor().run();
    }

    @Override
    public void run() {
        synchronized (this){
            while(this.isRunning){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void changeClientCnxnLogging() {
        ((Logger) LoggerFactory.getLogger(ClientCnxn.class)).setLevel(Level.WARN);
    }
}
