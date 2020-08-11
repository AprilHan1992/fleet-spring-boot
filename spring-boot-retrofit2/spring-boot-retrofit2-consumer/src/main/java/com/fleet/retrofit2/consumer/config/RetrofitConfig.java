package com.fleet.retrofit2.consumer.config;

import com.fleet.retrofit2.consumer.client.UserClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author April Han
 */
@Configuration
public class RetrofitConfig {

    private static final String BASE_URL = "http://localhost:8001/";

    private static final long TIME_OUT = 5000;

    @Bean
    public UserClient userClient() {
        return retrofit().create(UserClient.class);
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    return chain.proceed(request);
                })
                .build();
    }
}
