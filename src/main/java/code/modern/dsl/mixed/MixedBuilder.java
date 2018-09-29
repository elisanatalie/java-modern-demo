package code.modern.dsl.mixed;

import java.util.function.Consumer;
import java.util.stream.Stream;

import code.modern.dsl.model.Order;
import code.modern.dsl.model.Stock;
import code.modern.dsl.model.Trade;

public class MixedBuilder {
    public static Order forCustomer(final String customer, final TradeBuilder... builders) {
        final Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(b -> order.addTrade(b.trade));
        return order;
    }

    public static TradeBuilder buy(final Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.BUY);
    }

    public static TradeBuilder sell(final Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.SELL);
    }

    private static TradeBuilder buildTrade(final Consumer<TradeBuilder> consumer, final Trade.Type buy) {
        final TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(buy);
        consumer.accept(builder);
        return builder;
    }

    public static class TradeBuilder {

        private final Trade trade = new Trade();

        public TradeBuilder quantity(final int quantity) {
            trade.setQuantity(quantity);
            return this;
        }

        public TradeBuilder at(final double price) {
            trade.setPrice(price);
            return this;
        }

        public StockBuilder stock(final String symbol) {
            return new StockBuilder(this, trade, symbol);
        }
    }

    public static class StockBuilder {

        private final TradeBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(final TradeBuilder builder, final Trade trade, final String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TradeBuilder on(final String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return builder;
        }
    }
}
