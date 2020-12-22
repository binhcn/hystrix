package dev.binhcn.grpc;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dev.binhcn.proto.GreeterGrpc;
import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import rx.Observable;

public class RxGrpcServerHostNon extends GreeterGrpc.GreeterImplBase {
    public static void main(String[] args) throws Exception {
        // Start the server
        Server server = ServerBuilder.forPort(8080).addService(new RxGrpcServerHostNon()).build().start();
        server.awaitTermination();
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        RxGrpcAsyncClientNon client = new RxGrpcAsyncClientNon();
        ListenableFuture<HelloReply> future = client.sayHello(request);
        Futures.addCallback(future, new FutureCallback<HelloReply>() {
            @Override
            public void onSuccess(HelloReply helloReply) {
                responseObserver.onNext(helloReply);
                responseObserver.onCompleted();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });


    }


}