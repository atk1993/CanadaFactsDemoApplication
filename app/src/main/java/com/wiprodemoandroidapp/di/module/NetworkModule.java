package com.wiprodemoandroidapp.di.module;

import static com.wiprodemoandroidapp.di.module.UrlConstants.BASE_API_URL;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ict.assetmanagement.common.network.NetworkConnectivityProvider;
import com.ict.assetmanagement.common.network.NetworkConnectivityProviderContract;
import com.wiprodemoandroidapp.BuildConfig;
import com.wiprodemoandroidapp.di.annotations.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Represents the network module used by all the screen.
 * Class responsible to provide all the dependency requires to perform
 * the network call. Method exposed over here can be used across application.
 */
@Module
public class NetworkModule {
  public static final String RETROFIT_UNAUTHENTICATED = "unAuthenticated";
  static final String OK_HTTP_CLIENT_UNAUTHENTICATED = "unAuthenticated_okhttp_client";

  @Provides
  Retrofit.Builder providesRetrofitBuilder() {
    return new Retrofit.Builder();
  }

  @Provides
  @ApplicationScope
  NetworkConnectivityProviderContract providesNetworkConnectivityProviderContract(Context context) {
    return new NetworkConnectivityProvider(context);
  }

  @Provides
  @ApplicationScope
  NetworkConnectivityInterceptor providesNetworkInterceptor(
      NetworkConnectivityProviderContract networkConnectivityProvider, Context context) {
    return new NetworkConnectivityInterceptor(networkConnectivityProvider, context);
  }

  @Provides
  @ApplicationScope
  @Named(OK_HTTP_CLIENT_UNAUTHENTICATED)
  OkHttpClient providesUnAuthenticatedOkHttpClient(
      NetworkConnectivityInterceptor networkConnectivityInterceptor) {
    return createOkHttpClient(networkConnectivityInterceptor);
  }

  @Provides
  @ApplicationScope
  @Named(RETROFIT_UNAUTHENTICATED)
  Retrofit providesUnAuthenticatedRestAdapter(
      Retrofit.Builder builder,
      @Named(BASE_API_URL) String baseUrl,
      @Named(OK_HTTP_CLIENT_UNAUTHENTICATED) OkHttpClient okHttpClient) {
    return provideRetrofit(builder, baseUrl, okHttpClient);
  }

  private RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  private Retrofit provideRetrofit(Builder builder, String baseUrl, OkHttpClient okHttpClient) {
    return builder
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(provideGsonConverterFactory())
        .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
        .build();
  }

  private OkHttpClient createOkHttpClient(
      NetworkConnectivityInterceptor networkConnectivityInterceptor) {
    final int CONNECTION_TIMEOUT = 30;
    final int READ_TIMEOUT = 30;

    OkHttpClient.Builder builder =
        new OkHttpClient.Builder()
            .addInterceptor(networkConnectivityInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

    // Show the request and response log
    // Enable only in Debug Mode
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG) {
      loggingInterceptor.level(Level.BODY);
    } else {
      loggingInterceptor.level(Level.NONE);
    }
    builder.addInterceptor(loggingInterceptor);
    return builder.build();
  }

  @Provides
  @ApplicationScope
  GsonConverterFactory provideGsonConverterFactory() {
    Gson gson = new GsonBuilder().setLenient().create();
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @ApplicationScope
  RxJava2CallAdapterFactory provideRxJavaAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }
}
