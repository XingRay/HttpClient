package com.xingray.httpclient;

import okhttp3.ResponseBody;

import java.io.Closeable;
import java.io.IOException;

public class Response implements Closeable {

    private final okhttp3.Response mResponse;

    public Response(okhttp3.Response response) {
        mResponse = response;
    }

    public String bodyString() throws IOException {
        ResponseBody body = mResponse.body();
        return body == null ? null : body.string();
    }

    @Override
    public void close() throws IOException {
        mResponse.close();
    }
}
