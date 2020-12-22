package dev.binhcn.grpc;

import com.netflix.hystrix.*;
import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import rx.Observable;
import rx.Single;

import java.time.Duration;

public class CommandHelloWorld extends HystrixObservableCommand<HelloReply> {

    private final HelloRequest helloRequest;
    private final RxGrpcAsyncClient client;

    public CommandHelloWorld(HelloRequest helloRequest) {
//        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)));
        this.helloRequest = helloRequest;
        client = new RxGrpcAsyncClient();
    }

    @Override
    protected Observable<HelloReply> construct() {
        System.out.println("construct ---" + Thread.currentThread().getName());
        Single<HelloReply> observable = RxJavaInterop.toV1Single(client.sayHello(helloRequest));
        return observable.toObservable();
    }

    @Override
    protected Observable<HelloReply> resumeWithFallback() {
        return Observable.just(HelloReply.newBuilder().setMessage("hello binh").build())
                .map(n -> {
                    System.out.println("fallback ---" + Thread.currentThread().getName());
                    return n;
                });
    }

    public static void main(String[] args) throws InterruptedException {
        HelloRequest helloRequest = HelloRequest.newBuilder().setName(" world").build();
        CommandHelloWorld commandHelloWorld = new CommandHelloWorld(helloRequest);
        Observable<HelloReply> observable = commandHelloWorld.toObservable();
        observable.map(HelloReply::getMessage)
                .subscribe(System.out::println);

        Thread.sleep(Duration.ofSeconds(1).toMillis());
    }
}
