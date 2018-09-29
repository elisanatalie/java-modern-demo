package code.modern.pattern.template;

public class OnlineBankingUser {
    public static void main(String[] args) {
        new OnlineBanking().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));
        new OnlineBanking().processCustomer(1337, (Customer c) -> System.out.println("Here is a new credit card, " + c.getName()));
    }
}
