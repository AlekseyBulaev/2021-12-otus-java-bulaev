package ru.otus.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.otus.generated.GrpcRequest;
import ru.otus.generated.RemoteServiceGrpc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;
@Slf4j
public class GrpcClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;
    long current = 0;

    public static void main(String[] args) {
        try {
            var client = new GrpcClient();
            client.start();
        }catch (Exception e){
            log.error("Error in GrpcClient running start():", e);
        }
    }

    public void start() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT).usePlaintext().build();
        var stub = RemoteServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(10);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        GrpcRequest request = GrpcRequest.newBuilder().setStart(1).setLimit(10).build();
        var streamObserver = new GrpcResponseObserver();
        stub.generate(request, streamObserver);

        Runnable runnableTask = () -> {
            current = current + streamObserver.getValueAndReset() + 1;
            System.out.println("currentValue:" + current);
            latch.countDown();
        };

        executor.scheduleAtFixedRate(runnableTask, 0, 1, SECONDS);
        latch.await();

        channel.shutdown();
        executor.shutdown();
    }
}
