package com.example;

import java.util.Scanner;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class CommandListenerThread implements Runnable{

    private final ZooKeeper zooKeeper;

    private final String dfsStartPath;

    public CommandListenerThread(ZooKeeper zooKeeper, String dfsStartPath) {
        this.zooKeeper = zooKeeper;
        this.dfsStartPath = dfsStartPath;
    }

    private void commandLoop() {
        final var scanner = new Scanner(System.in);

        while (true) {
            if(scanner.nextLine().equals("dfs")) {
                try {
                    zooKeeper.getAllChildrenNumber(dfsStartPath);
                    nodeDFS(dfsStartPath);
                } catch (KeeperException | InterruptedException ignored) {
                    System.out.println("No tree");
                }
            }
        }
    }

    private void nodeDFS(String path) {
        System.out.println(path);

        try {
            zooKeeper
                    .getChildren(path, true)
                    .forEach(childName -> nodeDFS(path + "/" + childName));
        } catch (KeeperException | InterruptedException ignored) {
        }
    }

    @Override
    public void run() {
        commandLoop();
    }
}
