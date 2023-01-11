package com.linuxea.lrpc.client.net.socket;

import com.linuxea.lrpc.common.pool.ObjectPool;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

public class SocketPool implements SocketGenerate {

    private final ObjectPool<Socket> socketObjectPool;

    public SocketPool(Integer perMaxNum) {
        this.socketObjectPool = new ObjectPool<>(perMaxNum);
    }

    @Override
    public Socket get(String host, int port) {

        Supplier<Socket> socketSupplier = () -> {
            try {
                return new Socket(host, port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        return socketObjectPool.get(socketSupplier);
    }

    @Override
    public void close(Socket socket) {
        this.socketObjectPool.returnPool(socket);
    }


}
