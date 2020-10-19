package com.xingray.httpclient;

import java.io.Closeable;
import java.io.IOException;

public class Response implements Closeable {

    private final okhttp3.Response mResponse;

    public Response(okhttp3.Response response) {
        mResponse = response;
    }

    public String bodyString() throws IOException {
        return mResponse.body().string();
    }

    @Override
    public void close() throws IOException {
        mResponse.close();
    }
}
