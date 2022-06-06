package com.example;

import java.util.Arrays;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.KeeperException.Code;


public class StatCallbackImpl implements AsyncCallback.StatCallback {

    private final DataMonitorListener dataMonitorListener;

    private final ZooKeeper zooKeeper;

    private byte[] previousData;

    public StatCallbackImpl(DataMonitorListener dataMonitorListener,
                            ZooKeeper zooKeeper) {
        this.dataMonitorListener = dataMonitorListener;
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;

        switch (rc) {
            case Code.Ok -> exists = true;
            case Code.NoNode -> exists = false;
            case Code.NoAuth -> {
                dataMonitorListener.closing(rc);
                return;
            }
            default -> {
                zooKeeper.exists(ZWatcher.Z_NODE, true, this, null);
                return;
            }
        }

        byte[] b = null;
        if(exists) {
            try {
                b = zooKeeper.getData(ZWatcher.Z_NODE, false, null);
            } catch (KeeperException keeperException) {
                keeperException.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }

        if((b == null && previousData != null) || (b != null && !Arrays.equals(previousData, b))) {
            dataMonitorListener.exists(b);
            previousData = b;
        }
    }
}
