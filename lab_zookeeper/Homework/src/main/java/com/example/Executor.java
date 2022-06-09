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

    private static final String HOST_URL = "127.0.0.1:2191";

    private static final int SESSION_TIMEOUT = 3000;

    private final ZWatcher zWatcher;

    private final ZooKeeper zooKeeper;

    private final CommandListenerThread commandListenerThread;

    private boolean isRunning = true;

    public Executor() throws IOException {
        zooKeeper = new ZooKeeper(HOST_URL, SESSION_TIMEOUT, this);
        zWatcher = new ZWatcher(zooKeeper);
        commandListenerThread = new CommandListenerThread(zooKeeper, ZWatcher.Z_NODE);

        changeClientCnxnLogging();
        commandListenerThread();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getState()) {
            case Disconnected, AuthFailed, Closed -> {
                synchronized (this) {
                    isRunning=false;
                    notifyAll();
                }
            }
        }

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


        commandListenerThread.close();
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeClientCnxnLogging() {
        ((Logger) LoggerFactory.getLogger(ClientCnxn.class)).setLevel(Level.WARN);
    }

    private void commandListenerThread() {
        new Thread(commandListenerThread).start();
    }
}
