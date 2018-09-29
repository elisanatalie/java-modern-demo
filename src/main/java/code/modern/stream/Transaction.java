package code.modern.stream;

public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(final Trader trader, final int year, final int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{%s, year: %d, value: %d}", trader, year, value);
    }
}