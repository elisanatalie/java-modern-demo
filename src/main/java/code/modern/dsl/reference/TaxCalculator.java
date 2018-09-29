package code.modern.dsl.reference;

import java.util.function.DoubleUnaryOperator;

import code.modern.dsl.model.Order;

public class TaxCalculator {
    private DoubleUnaryOperator taxFunction = d -> d;

    public TaxCalculator with(final DoubleUnaryOperator f) {
        taxFunction = taxFunction.andThen(f);
        return this;
    }

    public double calculate(final Order order) {
        return taxFunction.applyAsDouble(order.getValue());
    }
}


