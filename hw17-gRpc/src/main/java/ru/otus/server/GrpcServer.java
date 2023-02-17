package ru.otus.server;

import io.grpc.ServerBuilder;
import ru.otus.service.RemoteCounterServiceImpl;

import java.io.IOException;

public class GrpcServer {
    public static final int SERVER_PORT = 8190;

    public static void main(String[] args)  throws IOException, InterruptedException {
        var remoteService = new RemoteCounterServiceImpl();

        var server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(remoteService).build();
        server.start();
        System.out.println("server waiting for client connections...");
        server.awaitTermination();
    }
}
