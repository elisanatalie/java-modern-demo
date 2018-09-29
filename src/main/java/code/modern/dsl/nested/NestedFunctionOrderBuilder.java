package code.modern.dsl.nested;

import java.util.stream.Stream;

import code.modern.dsl.model.Order;
import code.modern.dsl.model.Stock;
import code.modern.dsl.model.Trade;

public class NestedFunctionOrderBuilder {
    public static Order order(final String customer, final Trade... trades) {
        final Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(final int quantity, final Stock stock, final double price) {
        return buildTrade(quantity, stock, price, Trade.Type.BUY);
    }

    public static Trade sell(final int quantity, final Stock stock, final double price) {
        return buildTrade(quantity, stock, price, Trade.Type.SELL);
    }

    private static Trade buildTrade(final int quantity, final Stock stock, final double price, final Trade.Type buy) {
        final Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setType(buy);
        trade.setStock(stock);
        trade.setPrice(price);
        return trade;
    }

    public static double at(final double price) {
        return price;
    }

    public static Stock stock(final String symbol, final String market) {
        final Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(final String market) {
        return market;
    }
}

