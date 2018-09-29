package code.modern.dsl.model;

public class Stock {
    private String symbol;
    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(final String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}
