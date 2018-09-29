package code.modern.dsl;

import static code.modern.dsl.chaining.MethodChainingOrderBuilder.forCustomer;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.at;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.buy;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.on;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.order;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.sell;
import static code.modern.dsl.nested.NestedFunctionOrderBuilder.stock;

import code.modern.dsl.lambda.LambdaOrderBuilder;
import code.modern.dsl.mixed.MixedBuilder;
import code.modern.dsl.model.Order;
import code.modern.dsl.model.Tax;
import code.modern.dsl.reference.TaxCalculator;

public class DslUser {
    public static void main(final String[] args) {
        final Order order1 = forCustomer("BigBank").buy(80)
                                                   .stock("IBM")
                                                   .on("NYSE")
                                                   .at(125.00)
                                                   .sell(50)
                                                   .stock("GOOGLE")
                                                   .on("NASDAQ")
                                                   .at(375.00)
                                                   .end();
        System.out.println(order1);

        final Order order2 = order("BigBank",
                                   buy(80,
                                 stock("IBM", on("NYSE")),
                                 at(125.00)),
                                   sell(50,
                                  stock("GOOGLE", on("NASDAQ")),
                                  at(375.00))
        );

        System.out.println(order2);

        final Order order3 = LambdaOrderBuilder.order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });

        System.out.println(order3);

        final Order order4 = MixedBuilder.forCustomer("BigBank",
                                                      MixedBuilder.buy(t -> t.quantity(80)
                                                                       .stock("IBM")
                                                                       .on("NYSE")
                                                                       .at(125.00)),
                                                      MixedBuilder.sell(t -> t.quantity(50)
                                                                        .stock("GOOGLE")
                                                                        .on("NASDAQ")
                                                                        .at(375.00)));
        System.out.println(order4);

        final double value = new TaxCalculator()
                .with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order4);

        System.out.println(value);
    }
}
