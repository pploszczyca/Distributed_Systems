package com.example;

import java.io.IOException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Runnable, Watcher {

    private static final String HOST_PORT = "127.0.0.1:2181";
    private final ZWatcher zWatcher;

    private boolean isRunning = true;

    public Executor() throws IOException {
        final ZooKeeper zooKeeper = new ZooKeeper(HOST_PORT, 3000, this);
        zWatcher = new ZWatcher(zooKeeper);
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
}
