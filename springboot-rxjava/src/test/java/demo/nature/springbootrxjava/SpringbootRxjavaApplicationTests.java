package demo.nature.springbootrxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class SpringbootRxjavaApplicationTests {

    @Test
    void testHelloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    @Test
    void testObservable() {
//        Observable<String> observer = Observable.just("Hello"); // provides datea
//        observer.subscribe(System.out::println); // Callable as subscriber

        final List<String> list = Arrays.asList(
                "blue", "red", "green", "yellow", "orange", "cyan", "purple"
        );
//        Disposable disposable = Observable.create(emitter -> {
//            try {
//                for (String todo : list) {
//                    emitter.onNext(todo);
//                }
//                emitter.onComplete();
//            } catch (Exception e) {
//                emitter.onError(e);
//            }
//        }).subscribe(n -> {
//            System.out.println("onNext : " + n);
//        }, e -> {
//            System.out.println("onError : " + e.toString());
//
//        }, () -> {
//            System.out.println("onCompletion");
//        });


        Observable<String> observable = Observable.create(emitter -> {
            try {
                for (String todo : list) {
                    emitter.onNext(todo);
                }
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println( "onSubscribe: " + d);
            }
            @Override
            public void onNext(String string) {
                System.out.println( "onNext: " + string);
            }
            @Override
            public void onError(Throwable e) {
                System.out.println( "onError: " + e);
            }
            @Override
            public void onComplete() {
                System.out.println( "onComplete: ");
            }
        };

        observable.subscribe(observer);
    }

    private void assertTrue(boolean hello) {
    }

    /**
     * emitter: blue
     * Subscriber onNext,blue
     * emitter: red
     * Subscriber onNext,red
     * emitter: green
     * Subscriber onNext,green
     * emitter: yellow
     * Subscriber onNext,yellow
     * emitter: orange
     * Subscriber onNext,orange
     * emitter: cyan
     * Subscriber onNext,cyan
     * emitter: purple
     * Subscriber onNext,purple
     *
     * Publisher emit 和Subscriber onNext 都在同一个线程
     */
    @Test
    public void testFlowable()
    {
        final List<String> list = Arrays.asList(
                "blue", "red", "green", "yellow", "orange", "cyan", "purple"
        );

//        System.out.println("------fromList-----");
//        Flowable.fromIterable(list).skip(2).subscribe(System.out::println);
//        System.out.println("------fromArray-----");
//        Flowable.fromArray(list.toArray()).subscribe(System.out::println);
//
//        System.out.println("------fromJust-----");
//        Flowable.just("blue").subscribe(System.out::println);


        Subscriber<? super String> s = new Subscriber<String>()
        {
            @Override
            public void onSubscribe(Subscription s)
            {
                s.request(Long.MAX_VALUE);//请求多少事件，这里表示不限制

//                s.request(1);
//                System.out.println("onSubscribe," + s);
            }

            @Override
            public void onNext(String s)
            {
                System.out.println("Subscriber onNext," + s);
            }

            @Override
            public void onError(Throwable t)
            {
                System.out.println("onError," + t);
            }

            @Override
            public void onComplete()
            {
                System.out.println("onComplete!");
            }
        };
//        Flowable.fromIterable(list).subscribe(s);

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                for(String s: list){
                    System.out.println("emitter: " +s);
                    flowableEmitter.onNext(s);
                }

            }
        }, BackpressureStrategy.BUFFER).subscribe(s);
        System.out.println("------fromSubscribe END-----");

//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 如果你使用一个单一连续事件流，即只有一个onNext事件，接着就触发onComplete或者onError，这样你可以使用Single。
     */
    @Test
    public void tesSingle(){
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {
                e.onSuccess("test");
//                e.onSuccess("test2");//错误写法，重复调用也不会处理
            }
        });

//订阅观察者SingleObserver
        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                System.out.println("onSuccess," + s);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    /**
     *   如果你的观察者连onNext事件都不关心，你可以使用Completable，他只有onComplete和onError两个事件：
     */
    @Test
    public void testCompletable(){
        Completable.create(new CompletableOnSubscribe() {//被观察者

            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                e.onComplete();//单一onComplete或者onError
            }

        }).subscribe(new CompletableObserver() {//观察者
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Test
    public void testMaybe(){
        //被观察者
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {
                e.onSuccess("test");//发送一个数据的情况，或者onError，不需要再调用onComplete(调用了也不会触发onComplete回调方法)
                //e.onComplete();//不需要发送数据的情况，或者onError
            }
        });

//订阅观察者
        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                //发送一个数据时，相当于onNext和onComplete，但不会触发另一个方法onComplete
                System.out.println("onSuccess:" + s);
            }

            @Override
            public void onComplete() {
                //无数据发送时候的onComplete事件
                System.out.println( "onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println( "onError");

            }

        });
    }


    @Test
    public void testMap(){
        Flowable.just(1, 2, 3)
                .map(integer -> "int" + integer)
//                .subscribe(ele -> System.out.println( String.valueOf(ele)));
        .subscribe(System.out::println);
    }

    /**
     * flatMap中方法作用：
     *       将一个发射事件上游的Observable转换为多个发射事件的 Observable，
     *       然后把转换之后的多个事件合并后放到一个单独的 Observable里
     */
    @Test
    public void testFlatMapObservable(){
        // 创建一个上游：Observable
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }

            /**
             * flatMap中把上游发射来的3个事件，转换为一个新的发射3个String事件的水管，
             * 为了看到flatMap是无效的，下边延迟10ms
             */
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) {
                final List<String> list = new ArrayList<String>() ;
                for (int i = 0 ; i < 3 ; i++){
                    list.add("I am value " + integer) ;
                }
                return Observable.fromIterable(list);
            }

            // 建立连接、创建一个下游：Observer
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println( "accept s -> " + s);
            }
        });
    }

    @Test
    public void testFlatMapFlowable(){
        Flowable.just(1, 2, 3)
                .flatMap((Function<Integer, Publisher<?>>)
                        integer -> Flowable.just("a",integer))
                .subscribe(ele -> System.out.println("subscribe -> " +  String.valueOf(ele)));
    }

    @Test
    public void testConcatMap1(){
        Flowable.just(1, 2, 3)
                .concatMap(integer -> Flowable.just("a", integer))
                .subscribe(ele -> System.out.println("subscribe -> " +  String.valueOf(ele)));
    }


    @Test
    public void testConcatMap2() {
        System.out.println("######concatMap#####");
        Flowable flowable = Flowable.just(30, 80, 90).concatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<String> apply(Integer integer) throws Exception {
                return Flowable.just(integer + "#");
            }
        });

        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String item) throws Exception {
                System.out.println("这里是指定函数返回的Publisher发射的项：" + item);
            }
        });
    }

    /**
     * 从 concatMap 操作我们知道，concat 操作符肯定也是有序的，而 concat 操作符是接收若干个 Observables，发射数据是有序的，不会交叉。
     * concat: f1-1
     * concat: f1-2
     * concat: f1-3
     * concat: f1-4
     * concat: f1-5
     * concat: f1-6
     * concat: f1-7
     * concat: f1-8
     * concat: f1-9
     * concat: f1-10
     * concat: f2-1
     * concat: f2-2
     * concat: f2-3
     */
    @Test
    public void testConcat(){
        Flowable<String> f1 = Flowable.intervalRange(1, 10, 1, 1, TimeUnit.SECONDS).map(index -> "f1-" + index);
        Flowable<String> f2 = Flowable.intervalRange(1, 3, 2, 2, TimeUnit.SECONDS).map(index -> "f2-" + index);

//        Flowable.ambArray(f1, f2).map(x -> "amb: " + x).subscribe(System.out::println);
        System.out.println("----------concat-----------");
        Flowable.concat(f1, f2).map(x -> "concat: " + x).subscribe(System.out::println);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFromfuture(){
        Flowable publisher = Flowable.fromFuture(new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return "future is good";
            }

            @Override
            public String get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                System.out.println("timeout is " + timeout);

                return "future is good, but time too long";
            }
        },3, TimeUnit.SECONDS);

        publisher.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("value is " + s);
            }
        });
    }


}
