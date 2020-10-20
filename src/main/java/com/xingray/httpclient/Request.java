package com.xingray.httpclient;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private final okhttp3.Request.Builder builder;

    private Method method;
    private String url;
    private final Headers.Builder headerBuilder;
    private String mMediaType;
    private final HashMap<String, Object> params;

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

    public void addHeaders(List<KeyValue<String, String>> headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        for (KeyValue<String, String> keyValue : headers) {
            addHeader(keyValue.getKey(), keyValue.getValue());
        }
    }

    public void setMediaType(String mediaType) {
        mMediaType = mediaType;
    }

    public void addParam(String name, Object value) {
        params.put(name, value);
    }

    public void addParams(Map<String, String> params) {
        this.params.putAll(params);
    }

    okhttp3.Request build() {
        if (method == Method.GET) {
            if (!params.isEmpty()) {
                url += "?" + buildParams();
            }
            builder.url(url).headers(headerBuilder.build()).get();
        } else if (method == Method.POST) {
            RequestBody body = RequestBody.create(buildParams(), MediaType.parse(mMediaType));
            builder.url(url).headers(headerBuilder.build()).post(body);
        }
        return builder.build();
    }

    private String buildParams() {
        if (params.isEmpty()) {
            return "";
        }

        boolean isFirst = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (!isFirst) {
                builder.append('&');
            }
            isFirst = false;

            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return builder.toString();
    }
}
