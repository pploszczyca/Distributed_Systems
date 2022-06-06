package com.example;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZWatcher implements Watcher {

    public static final String Z_NODE = "/z";

    private final ZooKeeper zooKeeper;

    private final AsyncCallback.StatCallback statCallback;

    public ZWatcher(ZooKeeper zooKeeper,
                    AsyncCallback.StatCallback statCallback) {
        this.zooKeeper = zooKeeper;
        this.statCallback = statCallback;

        zooKeeper.exists(Z_NODE, true, statCallback, null);
    }

    @Override
    public void process(WatchedEvent event) {
        final var path = event.getPath();
        System.out.println(path);


        if(event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    break;
            }
        } else {
            if(path != null && path.equals(Z_NODE)) {
                zooKeeper.exists(Z_NODE, true, statCallback, null);
            }
        }

    }
}
