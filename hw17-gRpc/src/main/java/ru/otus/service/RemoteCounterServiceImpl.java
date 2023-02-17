package ru.otus.service;

import io.grpc.stub.StreamObserver;
import ru.otus.generated.GrpcRequest;
import ru.otus.generated.GrpcResponse;
import ru.otus.generated.RemoteServiceGrpc;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class RemoteCounterServiceImpl extends RemoteServiceGrpc.RemoteServiceImplBase {

    @Override
    public void generate(GrpcRequest request, StreamObserver<GrpcResponse> responseObserver) {
        System.out.println("Generate stream...");
        var current = new AtomicLong(request.getStart());
        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            var value = current.incrementAndGet();
            var response = GrpcResponse.newBuilder().setValue(value).build();
            responseObserver.onNext(response);
            if(value == request.getLimit()){
                executor.shutdown();
                responseObserver.onCompleted();
            }
        };

        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
}
