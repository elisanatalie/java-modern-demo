package code.modern.stream;

public class Trader {

    private final String name;
    private final String city;

    public Trader(final String n, final String c) {
        name = n;
        city = c;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Trader:%s in %s", name, city);
    }
}