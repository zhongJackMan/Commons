package com.jackman.commons.zookeeper.component;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author shusheng
 * @Date 18/11/8 下午2:54
 */
public class ZookeeperComponent implements InitializingBean {

    private volatile ZooKeeper zooKeeper;

    private volatile String connectString;

    private volatile Integer sessionTimeOut;


    private void createNode() {
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initialZooKeeper();
    }

    private void initialZooKeeper() throws Exception {
        if(null != zooKeeper) {
            try {
                zooKeeper.close();
            }catch(Exception e) {
                throw e;
            }
        }

        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, (WatchedEvent event) -> {
            switch(event.getState()) {
                case Expired:
                    try {
                        initialZooKeeper();
                    }catch(Exception e) {}
                    break;
                case SyncConnected:
                    break;
                case Disconnected:
                    //disconnected之后zooKeeper会自动重连这里不用管
                    break;
                default:
                    break;
            }
        });

    }
}
