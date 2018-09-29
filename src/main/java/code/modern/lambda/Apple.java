package code.modern.lambda;

public class Apple implements Fruit {
    private int weight;
    private final Color color;

    public Apple(final int weight, final Color color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public Color getColor() {
        return color;
    }
}

