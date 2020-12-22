package dev.binhcn.grpc;

import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import dev.binhcn.proto.RxGreeterGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.reactivex.Single;

public class RxGrpcServer extends RxGreeterGrpc.GreeterImplBase {
    public static void main(String[] args) throws Exception {
        // Start the server
        Server server = ServerBuilder.forPort(8888).addService(new RxGrpcServer()).build().start();
        server.awaitTermination();
    }

    /**
     * Implement a UNARY operation
     */
    @Override
    public Single<HelloReply> sayHello(Single<HelloRequest> request) {
        return request
                .map(HelloRequest::getName)
                .map(name -> "Hello " + name)
                .map(greeting -> HelloReply.newBuilder().setMessage(greeting).build());
    }



}