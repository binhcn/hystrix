package dev.binhcn.grpc;

import dev.binhcn.proto.GreeterGrpc;
import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import dev.binhcn.proto.RxGreeterGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.reactivex.Single;
import rx.Observable;

public class RxGrpcServerHost extends GreeterGrpc.GreeterImplBase {
    public static void main(String[] args) throws Exception {
        // Start the server
        Server server = ServerBuilder.forPort(8080).addService(new RxGrpcServerHost()).build().start();
        server.awaitTermination();
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld(request);
        Observable<HelloReply> observable = commandHelloWorld.toObservable();
        System.out.println("host ---" + Thread.currentThread().getName());
        observable.subscribe(res -> {
            System.out.println("final ---" + Thread.currentThread().getName());
            responseObserver.onNext(res);
            responseObserver.onCompleted();
        });

    }


}