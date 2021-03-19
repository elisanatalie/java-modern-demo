package code.modern.flow;

import java.util.concurrent.Flow.Publisher;

public class TempMain {
    public static void main(final String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber());
    }

    private static Publisher<TempInfo> getTemperatures(final String town) {
        return subscriber -> {
            final TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(subscriber, town));
        };
    }
}
