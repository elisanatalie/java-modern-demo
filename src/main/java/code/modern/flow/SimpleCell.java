package code.modern.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.IntConsumer;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private final String name;
    private final List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

    public SimpleCell(final String name) {
        this.name = name;
    }

    @Override
    public void subscribe(final Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    public void subscribe(final IntConsumer onNext) {
        subscribers.add(new Subscriber<>() {

            @Override
            public void onComplete() {
            }

            @Override
            public void onError(final Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onNext(final Integer val) {
                onNext.accept(val);
            }

            @Override
            public void onSubscribe(final Subscription s) {
            }
        });
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(value));
    }

    @Override
    public void onNext(final Integer newValue) {
        value = newValue;
        System.out.println(name + ":" + value);
        notifyAllSubscribers();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(final Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onSubscribe(final Subscription s) {
    }
}