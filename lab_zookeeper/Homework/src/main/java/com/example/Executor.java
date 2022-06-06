package com.example;

import java.io.IOException;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class Executor {

    private final ZooKeeper zooKeeper;

    public Executor() throws IOException {
        final String hostPort = "127.0.0.1:2181";
        final ZWatcher zWatcher = new ZWatcher();
        zooKeeper = new ZooKeeper(hostPort, 3000, zWatcher);

        addWatch();
    }

   private void addWatch() {
        while(true) {
            try {
                zooKeeper.addWatch(ZWatcher.Z_NODE, AddWatchMode.PERSISTENT_RECURSIVE);
            } catch (KeeperException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
   }

    public static void main(String[] args) throws IOException {
        new Executor();
    }
}
