package code.modern.future.building;

public class Car {
    private final Wheel wheel;
    private final Engine engine;
    private final Frame frame;

    public Car(final Wheel wheel, final Engine engine, final Frame frame) {
        this.wheel = wheel;
        this.engine = engine;
        this.frame = frame;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Wheel wheel;
        private Engine engine;
        private Frame frame;

        public Builder wheel(final Wheel wheel) {
            this.wheel = wheel;
            return this;
        }

        public Builder engine(final Engine engine) {
            this.engine = engine;
            return this;
        }

        public Builder frame(final Frame frame) {
            this.frame = frame;
            return this;
        }

        public Car build() {
            return new Car(wheel, engine, frame);
        }
    }
}

