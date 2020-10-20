package com.xingray.httpclient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * If you have any questions, you can contact by email { wangzhumoo@gmail.com}
 *
 * @author 王诛魔 2018/1/10 下午5:07
 * 支持https的工具类
 */

public class HttpUtil {

    public static List<KeyValue<String, String>> parseRawHeaders(String rawHeaders) {
        if (rawHeaders == null || rawHeaders.isBlank()) {
            return null;
        }

        String[] headerPairs = rawHeaders.split("\n");
        if (headerPairs.length == 0) {
            return null;
        }
        List<KeyValue<String, String>> headers = new ArrayList<>(headerPairs.length);

        for (String headerPair : headerPairs) {
            String[] split = headerPair.split(": ");
            if (split.length < 2) {
                continue;
            }

            headers.add(new KeyValue<>(split[0], split[1]));
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

