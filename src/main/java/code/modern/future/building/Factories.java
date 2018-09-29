package code.modern.future.building;

public class Factories {
    public static Engine createEngine() {
        delay();
        return new Engine("bmw");
    }

    public static Wheel createWheel() {
        delay();
        return new Wheel("123");
    }

    public static Frame createFrame() {
        delayLonger();
        return new Frame("titanium");
    }

    private static void delay() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void delayLonger() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
