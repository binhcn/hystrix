package dev.binhcn.proto;

import static dev.binhcn.proto.GreeterGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by RxGrpc generator",
comments = "Source: hello-world.proto")
public final class RxGreeterGrpc {
    private RxGreeterGrpc() {}

    public static RxGreeterStub newRxStub(io.grpc.Channel channel) {
        return new RxGreeterStub(channel);
    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static final class RxGreeterStub extends io.grpc.stub.AbstractStub<RxGreeterStub> {
        private GreeterGrpc.GreeterStub delegateStub;

        private RxGreeterStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GreeterGrpc.newStub(channel);
        }

        private RxGreeterStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GreeterGrpc.newStub(channel).build(channel, callOptions);
        }

        @java.lang.Override
        protected RxGreeterStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new RxGreeterStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.reactivex.Single<dev.binhcn.proto.HelloReply> sayHello(io.reactivex.Single<dev.binhcn.proto.HelloRequest> rxRequest) {
            return com.salesforce.rxgrpc.stub.ClientCalls.oneToOne(rxRequest,
                new com.salesforce.reactivegrpc.common.BiConsumer<dev.binhcn.proto.HelloRequest, io.grpc.stub.StreamObserver<dev.binhcn.proto.HelloReply>>() {
                    @java.lang.Override
                    public void accept(dev.binhcn.proto.HelloRequest request, io.grpc.stub.StreamObserver<dev.binhcn.proto.HelloReply> observer) {
                        delegateStub.sayHello(request, observer);
                    }
                }, getCallOptions());
        }

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.reactivex.Single<dev.binhcn.proto.HelloReply> sayHello(dev.binhcn.proto.HelloRequest rxRequest) {
            return com.salesforce.rxgrpc.stub.ClientCalls.oneToOne(io.reactivex.Single.just(rxRequest),
                new com.salesforce.reactivegrpc.common.BiConsumer<dev.binhcn.proto.HelloRequest, io.grpc.stub.StreamObserver<dev.binhcn.proto.HelloReply>>() {
                    @java.lang.Override
                    public void accept(dev.binhcn.proto.HelloRequest request, io.grpc.stub.StreamObserver<dev.binhcn.proto.HelloReply> observer) {
                        delegateStub.sayHello(request, observer);
                    }
                }, getCallOptions());
        }

    }

    /**
     * <pre>
     *  The greeting service definition.
     * </pre>
     */
    public static abstract class GreeterImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         *  Sends a greeting
         * </pre>
         */
        public io.reactivex.Single<dev.binhcn.proto.HelloReply> sayHello(io.reactivex.Single<dev.binhcn.proto.HelloRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            dev.binhcn.proto.GreeterGrpc.getSayHelloMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            dev.binhcn.proto.HelloRequest,
                                            dev.binhcn.proto.HelloReply>(
                                            this, METHODID_SAY_HELLO)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

    }

    public static final int METHODID_SAY_HELLO = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final GreeterImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SAY_HELLO:
                    com.salesforce.rxgrpc.stub.ServerCalls.oneToOne((dev.binhcn.proto.HelloRequest) request,
                            (io.grpc.stub.StreamObserver<dev.binhcn.proto.HelloReply>) responseObserver,
                            new com.salesforce.reactivegrpc.common.Function<io.reactivex.Single<dev.binhcn.proto.HelloRequest>, io.reactivex.Single<dev.binhcn.proto.HelloReply>>() {
                                @java.lang.Override
                                public io.reactivex.Single<dev.binhcn.proto.HelloReply> apply(io.reactivex.Single<dev.binhcn.proto.HelloRequest> single) {
                                    return serviceImpl.sayHello(single);
                                }
                            });
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
