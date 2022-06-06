package com.example;

import java.io.IOException;
import java.util.Optional;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZWatcher implements Watcher {

    public static final String Z_NODE = "/z";

    private Optional<Process> childProcess = Optional.empty();


    @Override
    public void process(WatchedEvent event) {
        final var path = event.getPath();
        System.out.println(path);

        if(event.getState() == Event.KeeperState.Expired) {
            return;
        }

        if(path.equals(Z_NODE)) {
            switch (event.getType()) {
                case NodeCreated -> startProcess();
                case NodeDeleted -> endProcess();
            }
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
