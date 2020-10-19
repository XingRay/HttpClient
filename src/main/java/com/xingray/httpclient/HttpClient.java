package com.xingray.httpclient;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HttpClient {

    private final ConcurrentHashMap<String, List<Cookie>> mCookieStore = new ConcurrentHashMap<>();
    private final OkHttpClient client;

    public HttpClient() {
        client = new OkHttpClient.Builder()
                .hostnameVerifier((s, sslSession) -> true)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        mCookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = mCookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
    }

    public String doRequest(Request request) {
        try {
            try (Response response = new Response(client.newCall(request.build()).execute())) {
                return response.bodyString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
