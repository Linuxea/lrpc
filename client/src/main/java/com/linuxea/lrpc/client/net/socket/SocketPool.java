package com.linuxea.lrpc.client.net.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SocketPool implements SocketGenerate {

    private final Integer perMaxNum;
    private volatile Map<String, Socket> socketCache;

    public SocketPool(Integer perMaxNum) {
        this.perMaxNum = perMaxNum;
        this.socketCache = new HashMap<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> socketCache.values().forEach(socket -> {
            try {
                System.out.println("close socket");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })));
    }

    @Override
    public Socket get(String host, int port) throws Exception {

        int randomNum = new Random().nextInt(perMaxNum);
        String useSocketKey = host + ":" + port + ":" + randomNum;
        Socket useSocket = socketCache.get(useSocketKey);
        if (useSocket != null) {
            return useSocket;
        } else {
            synchronized (this) {
                if (useSocket == null) {
                    Socket socket = new Socket(host, port);
                    this.socketCache.put(useSocketKey, socket);
                    return socket;
                } else {
                    return socketCache.get(useSocketKey);
                }
            }
        }
    }
}
