package code.modern.pattern.template;

import java.util.function.Consumer;

public class OnlineBanking {
    public void processCustomer(final int id, final Consumer<Customer> makeCustomerHappy) {
        final Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}


