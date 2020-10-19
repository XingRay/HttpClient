package com.xingray.httpclient;


import java.util.HashMap;
import java.util.Map;


public class HttpUtil {

    public static Map<String, String> parseRawHeaders(String rawHeaders) {
        if (rawHeaders == null || rawHeaders.isBlank()) {
            return null;
        }
        Map<String, String> headers = new HashMap<>();
        String[] headerPairs = rawHeaders.split("\n");

        for (String headerPair : headerPairs) {
            String[] split = headerPair.split(": ");
            headers.put(split[0], split[1]);
        }

        return headers;
    }

    public static Map<String, String> parseRawParams(String rawParams) {
        if (rawParams == null || rawParams.isBlank()) {
            return null;
        }
        Map<String, String> params = new HashMap<>();
        String[] paramPairs = rawParams.split("&");

        for (String paramPair : paramPairs) {
            String[] split = paramPair.split("=");
            params.put(split[0], split[1]);
        }

        return params;
    }
}

