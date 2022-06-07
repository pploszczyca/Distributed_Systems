package com.example;

import java.io.IOException;
import java.util.Optional;
import org.apache.zookeeper.AddWatchMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZWatcher implements Watcher {

    public static final String Z_NODE = "/z";

    private Optional<Process> childProcess = Optional.empty();

    private final ZooKeeper zooKeeper;

    public ZWatcher(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;

        addWatch();
    }

    @Override
    public void process(WatchedEvent event) {
        final var path = event.getPath();

        if(event.getState() == Event.KeeperState.Expired) {
            return;
        }

        if(path.equals(Z_NODE)) {
            switch (event.getType()) {
                case NodeCreated -> startProcess();
                case NodeDeleted -> endProcess();
            }
        }

        if(path.startsWith(Z_NODE)) {
            addWatch();

            switch (event.getType()) {
                case NodeCreated, NodeDeleted -> printAllChildrenNumber();
            }
        }
    }

    private void printAllChildrenNumber() {
        try {
            System.out.println("CHILDREN NUMBER: " + zooKeeper.getAllChildrenNumber(Z_NODE));
        } catch (KeeperException | InterruptedException ignored) {
        }
    }

    private void addWatch() {
        try {
            zooKeeper.addWatch(Z_NODE, AddWatchMode.PERSISTENT_RECURSIVE);
        } catch (KeeperException | InterruptedException ignored) {
        }
    }

    private void startProcess() {
        try {
            System.out.println("Starting child");
            childProcess = Optional.of(Runtime.getRuntime().exec("gedit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void endProcess() {
        childProcess.ifPresent(process -> {
            System.out.println("Killing process");
            process.destroy();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
            }
        });
        childProcess = Optional.empty();
    }
}
