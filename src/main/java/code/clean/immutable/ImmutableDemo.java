package code.clean.immutable;

public class ImmutableDemo {

    public static final String HTTP_LOCALHOST = "http://localhost";

    public static void main(final String[] args) {
        temporalCoupling();
        sideEffect();
    }

    private static void temporalCoupling() {
        System.out.println("=====TEMPORAL=====");
        final MutableRequest mutableRequest = new MutableRequest(HTTP_LOCALHOST);
//        mutableRequest.method("POST");
//        mutableRequest.fetch();
        mutableRequest.body("text=hello");
        mutableRequest.fetch();

        final ImmutableRequest immutableRequest = new ImmutableRequest(HTTP_LOCALHOST);
        immutableRequest.method("GET").fetch();
        immutableRequest.method("POST").body("text=hello").fetch();
    }

    private static void sideEffect() {
        System.out.println("=====SIDE EFFECT=====");
        final MutableRequest mutableRequest = new MutableRequest(HTTP_LOCALHOST);
        mutableRequest.method("GET");
        mutableRequest.post(mutableRequest);
        mutableRequest.fetch();

        final ImmutableRequest immutableRequest = new ImmutableRequest(HTTP_LOCALHOST);
        immutableRequest.method("GET").fetch();
        immutableRequest.post(immutableRequest);
        immutableRequest.method("GET").fetch();
    }
}
