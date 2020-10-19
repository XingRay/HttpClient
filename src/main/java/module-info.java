module HttpClient {
    requires okhttp3;

    opens com.xingray.httpclient;
    exports com.xingray.httpclient;
}