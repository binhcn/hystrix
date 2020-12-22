package dev.binhcn.grpc;

import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import dev.binhcn.proto.RxGreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RxGrpcAsyncClient {
    private ManagedChannel channel;
    private RxGreeterGrpc.RxGreeterStub stub;

    public RxGrpcAsyncClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext(true).build();
        stub = RxGreeterGrpc.newRxStub(channel);
    }

    public Single<HelloReply> sayHello(HelloRequest helloRequest) {
        System.out.println("client call method ---" + Thread.currentThread().getName());
        Single<HelloRequest> request = Single.just(helloRequest);
        return request.map(n -> {
            System.out.println("before client call ---" + Thread.currentThread().getName());
            return n;
        }).flatMap(stub::sayHello).map(n -> {
            System.out.println("after client call---" + Thread.currentThread().getName());
            return n;
        });
    }
}