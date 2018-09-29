package code.clean.immutable;

public class ImmutableRequest {
    private final String url;
    private final String method;
    private final String body;

    public ImmutableRequest(final String url) {
        this.url = url;
        method = "default method";
        body = "default request body";
    }

    public ImmutableRequest(final String url, final String method, final String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    public ImmutableRequest method(final String method) {
        return new ImmutableRequest(url, method, body);
    }

    public ImmutableRequest body(final String body) {
        return new ImmutableRequest(url, method, body);
    }

    public String fetch() {
        System.out.println(String.format("Requesting from URL: %s, with method : %s, with body: %s", url, method, body));
        return "response from url";
    }

    public String post(ImmutableRequest immutableRequest) {
        return immutableRequest.method("POST").fetch();
    }
}
