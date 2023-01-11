package com.linuxea.lrpc.client.net.socket;

import java.net.Socket;

public class SocketPrototypeGenerate implements SocketGenerate {

    @Override
    public Socket get(String host, int port) throws Exception {
        return new Socket(host, port);

    }

    @Override
    public void close(Socket socket) throws Exception {
        socket.close();
    }
}
