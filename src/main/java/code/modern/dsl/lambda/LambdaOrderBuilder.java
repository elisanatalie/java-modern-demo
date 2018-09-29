package code.modern.dsl.lambda;

import java.util.function.Consumer;

import code.modern.dsl.model.Order;
import code.modern.dsl.model.Stock;
import code.modern.dsl.model.Trade;

public class LambdaOrderBuilder {
    private final Order order = new Order();

    public static Order order(final Consumer<LambdaOrderBuilder> consumer) {
        final LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);
        return builder.order;
    }

    public void forCustomer(final String customer) {
        order.setCustomer(customer);
    }

    public void buy(final Consumer<TradeBuilder> consumer) {
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(final Consumer<TradeBuilder> consumer) {
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(final Consumer<TradeBuilder> consumer, final Trade.Type type) {
        final TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);
        consumer.accept(builder);
        order.addTrade(builder.trade);
    }

    public static class TradeBuilder {

        private final Trade trade = new Trade();

        public void quantity(final int quantity) {
            trade.setQuantity(quantity);
        }

        public void price(final double price) {
            trade.setPrice(price);
        }

        public void stock(final Consumer<StockBuilder> consumer) {
            final StockBuilder builder = new StockBuilder();
            consumer.accept(builder);
            trade.setStock(builder.stock);
        }
    }

    public static class StockBuilder {

        private final Stock stock = new Stock();

        public void symbol(final String symbol) {
            stock.setSymbol(symbol);
        }

        public void market(final String market) {
            stock.setMarket(market);
        }
    }
}


