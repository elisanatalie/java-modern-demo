package code.modern.dsl.chaining;

import code.modern.dsl.model.Order;
import code.modern.dsl.model.Stock;
import code.modern.dsl.model.Trade;

public class MethodChainingOrderBuilder {
    public final Order order = new Order();

    private MethodChainingOrderBuilder(final String customer) {
        order.setCustomer(customer);
    }

    public static MethodChainingOrderBuilder forCustomer(final String customer) {
        return new MethodChainingOrderBuilder(customer);
    }

    public Order end() {
        return order;
    }

    public TradeBuilder buy(final int quantity) {
        return new TradeBuilder(this, Trade.Type.BUY, quantity);
    }

    public TradeBuilder sell(final int quantity) {
        return new TradeBuilder(this, Trade.Type.SELL, quantity);
    }

    private MethodChainingOrderBuilder addTrade(final Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public static class TradeBuilder {

        private final MethodChainingOrderBuilder builder;
        public final Trade trade = new Trade();

        private TradeBuilder(final MethodChainingOrderBuilder builder, final Trade.Type type, final int quantity) {
            this.builder = builder;
            trade.setType(type);
            trade.setQuantity(quantity);
        }

        public StockBuilder stock(final String symbol) {
            return new StockBuilder(builder, trade, symbol);
        }
    }

    public static class TradeBuilderWithStock {

        private final MethodChainingOrderBuilder builder;
        private final Trade trade;

        public TradeBuilderWithStock(final MethodChainingOrderBuilder builder, final Trade trade) {
            this.builder = builder;
            this.trade = trade;
        }

        public MethodChainingOrderBuilder at(final double price) {
            trade.setPrice(price);
            return builder.addTrade(trade);
        }
    }

    public static class StockBuilder {

        private final MethodChainingOrderBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(final MethodChainingOrderBuilder builder, final Trade trade, final String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TradeBuilderWithStock on(final String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return new TradeBuilderWithStock(builder, trade);
        }
    }
}



