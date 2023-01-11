package com.linuxea.lrpc.client.net.socket;

import java.net.Socket;

/**
 * socket 生产
 */
public interface SocketGenerate {

    /**
     * 获取 socket
     *
     * @param host host
     * @param port port
     * @return Socket
     */
    Socket get(String host, int port) throws Exception;


    /**
     * socket 归还
     *
     * @param socket client socket
     * @throws Exception
     */
    void close(Socket socket) throws Exception;

}
