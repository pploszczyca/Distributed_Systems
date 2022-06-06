package com.example;

import java.io.IOException;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher, Runnable {

    public final ZWatcher zWatcher;

    public Executor() throws IOException {
        final DataMonitorListener dataMonitorListener = new DataMonitorListenerImpl();
        final String hostPort = "127.0.0.1:2181";
        final ZooKeeper zooKeeper = new ZooKeeper(hostPort, 3000, this);
        final AsyncCallback.StatCallback statCallback = new StatCallbackImpl(
                dataMonitorListener, zooKeeper);
        this.zWatcher = new ZWatcher(zooKeeper, statCallback);
    }

    @Override
    public void process(WatchedEvent event) {
        zWatcher.process(event);
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {

        }
    }

    public static void main(String[] args) throws IOException {
        new Executor().run();
    }
}
