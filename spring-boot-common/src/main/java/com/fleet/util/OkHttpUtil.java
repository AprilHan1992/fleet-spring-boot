package com.fleet.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author April Han
 */
public class OkHttpUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();

    private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static String get(String url) {
        String result = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void get(String url, Callback callback) {
        executorService.execute(() -> {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        });
    }

    public static String post(String url, String json) {
        String result = null;
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void post(String url, String json, Callback callback) {
        executorService.execute(() -> {
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        });
    }

    public static String post(String url, Map<String, String> params) {
        String result = null;
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            formBodyBuilder.add(key, value);
        }
        FormBody formBody = formBodyBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void post(String url, Map<String, String> params, Callback callback) {
        executorService.execute(() -> {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            for (String key : params.keySet()) {
                String value = params.get(key);
                formBodyBuilder.add(key, value);
            }
            FormBody formBody = formBodyBuilder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        });
    }

    public static void main(String[] args) {
        OkHttpUtil.get("https://www.baidu.com/", new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = Objects.requireNonNull(response.body()).string();
                    System.out.println(result);
                }
            }
        });
    }
}
