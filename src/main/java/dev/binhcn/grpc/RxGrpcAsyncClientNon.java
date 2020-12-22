package dev.binhcn.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import dev.binhcn.proto.GreeterGrpc;
import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import dev.binhcn.proto.RxGreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.Single;

public class RxGrpcAsyncClientNon {
    private ManagedChannel channel;
    private GreeterGrpc.GreeterFutureStub stub;

    public RxGrpcAsyncClientNon() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8888).usePlaintext(true).build();
        stub = GreeterGrpc.newFutureStub(channel);
    }

    public ListenableFuture<HelloReply> sayHello(HelloRequest helloRequest) {
        return stub.sayHello(helloRequest);
    }
}