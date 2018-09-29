package code.modern.pattern.observer;

public class ObserverUser {
    public static void main(final String[] args) {
        Feed f = new Feed();

        f.registerObserver((String tweet) -> {
            if ((tweet != null) && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f.registerObserver((String tweet) -> {
            if ((tweet != null) && tweet.contains("queen")) {
                System.out.println("Yet more news from London... " + tweet);
            }
        });

        f.notifyObservers("Suspected money launderer got caught.");
        f.notifyObservers("The queen cuts the ribbon.");
    }
}
