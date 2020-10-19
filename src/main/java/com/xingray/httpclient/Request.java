package com.xingray.httpclient;

import okhttp3.Headers;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private final okhttp3.Request.Builder builder;

    private Method method;
    private String url;
    private final Headers.Builder headerBuilder;
    private String mMediaType;
    private final HashMap<String, String> params;

    public Request() {
        builder = new okhttp3.Request.Builder();
        headerBuilder = new Headers.Builder();
        params = new HashMap<>();
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addHeader(String name, String value) {
        headerBuilder.add(name, value);
    }

    public void addHeaders(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());
        }
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public void addParams(Map<String, String> params) {
        this.params.putAll(params);
    }

    okhttp3.Request build() {
        builder.url(url).headers(headerBuilder.build());
        if (method == Method.GET) {
            builder.get();
        } else if (method == Method.POST) {
            builder.post(RequestBody.create(buildParams(), okhttp3.MediaType.parse(mMediaType)));
        }
        return builder.build();
    }

    private String buildParams() {
        if (params.isEmpty()) {
            return "";
        }

        boolean isFirst = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!isFirst) {
                builder.append('&');
            }
            isFirst = false;

            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return builder.toString();
    }
}
