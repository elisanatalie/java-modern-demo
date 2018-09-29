package code.modern.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;

public class TempSubscription implements Flow.Subscription {
    private final Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(final Subscriber<? super TempInfo> subscriber, final String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(final long n) {
        for (long i = 0L; i < n; i++) {
            try {
                subscriber.onNext(TempInfo.fetch(town));
            } catch (final Exception e) {
                subscriber.onError(e);
                break;
            }
        }
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}

