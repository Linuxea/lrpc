package com.linuxea.lrpc.server;

public abstract class RpcServer {

    /**
     * open port
     */
    protected int port;
    protected RegistryServer registryServer;

    public RpcServer(int port, RegistryServer registryServer) {
        this.port = port;
        this.registryServer = registryServer;
    }

    public abstract void start() throws Exception;


    public abstract void stop() throws Exception;

}
