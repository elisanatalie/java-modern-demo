package code.modern.pattern.template;

public class Database {
    public static Customer getCustomerWithId(final int id) {
        return new Customer(id, "Minnie");
    }
}
