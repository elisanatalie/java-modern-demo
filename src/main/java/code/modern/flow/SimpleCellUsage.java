package code.modern.flow;

public class SimpleCellUsage {
    public static void main(final String[] args) {
        final ArithmeticCell c3 = new ArithmeticCell("C3");
        final ArithmeticCell c5 = new ArithmeticCell("C5");
        final SimpleCell c4 = new SimpleCell("C4");
        final SimpleCell c2 = new SimpleCell("C2");
        final SimpleCell c1 = new SimpleCell("C1");
        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);
        c3.subscribe(c5::setLeft);
        c4.subscribe(c5::setRight);
        c1.onNext(10); // Update value of C1 to 10
        c2.onNext(20); // update value of C2 to 20
        c1.onNext(15); // update value of C1 to 15
        c4.onNext(1); // update value of C4 to 1
        c4.onNext(3); // update value of C4 to 3
    }
}
