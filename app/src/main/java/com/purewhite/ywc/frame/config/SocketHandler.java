package com.purewhite.ywc.frame.config;

public class SocketHandler implements Runnable{

    private String ip;
    private int port;
    public SocketHandler(String  ip,int port) {
        this.ip=ip;
        this.port=port;
    }

    @Override
    public void run() {

    }
}
