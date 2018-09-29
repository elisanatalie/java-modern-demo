package code.clean.immutable;

public class MutableRequest {
    private final String url;
    private String method;
    private String body;

    public MutableRequest(final String url) {
        this.url = url;
        method = "default method";
        body = "default request body";
    }

    public void method(final String method) {
        this.method = method;
    }

    public void body(final String body) {
        this.body = body;
    }

    public String fetch() {
        System.out.println(String.format("Requesting from URL: %s, with method : %s, with body: %s", url, method, body));
        return "response from url";
    }

    public String post(final MutableRequest request) {
        request.method("POST");
        return request.fetch();
    }
}
