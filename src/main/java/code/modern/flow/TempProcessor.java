package code.modern.flow;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TempProcessor implements Processor<TempInfo, TempInfo> {
    private Subscriber<? super TempInfo> subscriber;

    @Override
    public void subscribe(final Subscriber<? super TempInfo> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onNext(final TempInfo temp) {
        subscriber.onNext(new TempInfo(temp.getTown(), ((temp.getTemp() - 32) * 5) / 9));
    }

    @Override
    public void onSubscribe(final Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onError(final Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
