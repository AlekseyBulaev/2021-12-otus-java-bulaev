package ru.otus.client;

import io.grpc.stub.StreamObserver;
import ru.otus.generated.GrpcResponse;

public class GrpcResponseObserver implements StreamObserver<GrpcResponse> {
    private long actual = 0;

    private synchronized void setActual(long value){
        this.actual = value;
    }

    public synchronized long getValue(){
        var current = this.actual;
        this.actual = 0;
        return current;
    }

    @Override
    public void onNext(GrpcResponse value) {
        setActual(value.getValue());
        System.out.println("число от сервера:" + actual);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Exception");
        t.printStackTrace();
    }

    @Override
    public void onCompleted() {
        System.out.println("Finished");
    }
}
