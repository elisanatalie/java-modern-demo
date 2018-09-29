package code.modern.future.simple;

import static code.modern.future.Util.delay;

public class ExchangeService {

    public static final double DEFAULT_RATE = 1.35;

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double rate;

        Money(final double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(final Money source, final Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(final Money source, final Money destination) {
        delay();
        return destination.rate / source.rate;
    }
}
