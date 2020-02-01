package com.wiprodemoandroidapp.di.module;

import static com.wiprodemoandroidapp.di.module.NetworkModule.OK_HTTP_CLIENT_UNAUTHENTICATED;
import static com.wiprodemoandroidapp.di.module.UrlConstants.BASE_API_URL;
import static com.wiprodemoandroidapp.di.module.UrlConstants.BASE_PATH;
import static com.wiprodemoandroidapp.di.module.UrlConstants.CANADA_FACTS_BASE_URL;
import static com.wiprodemoandroidapp.di.module.UrlConstants.CANADA_FACTS_ENDPOINT;

import com.wiprodemoandroidapp.di.annotations.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CanadaFactsNetworkModule {

  public static final String CANADA_FACTS_RETROFIT = "CanadaFactsRetrofit";

  @Provides
  @ApplicationScope
  @Named(CANADA_FACTS_BASE_URL)
  String providesCanadaFactsUrl(
      @Named(BASE_API_URL) String gatewayUrl,
      @Named(BASE_PATH) String basePath,
      @Named(CANADA_FACTS_ENDPOINT) String assetMgmtEndPoint) {
    return gatewayUrl.concat(basePath).concat(assetMgmtEndPoint);
  }

  @Provides
  @ApplicationScope
  @Named(CANADA_FACTS_RETROFIT)
  Retrofit provideCanadaFactsRetrofit(
      @Named(CANADA_FACTS_BASE_URL) String url,
      @Named(OK_HTTP_CLIENT_UNAUTHENTICATED) OkHttpClient okHttpClient,
      RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
      GsonConverterFactory gsonConverterFactory) {
    return new Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build();
  }
}
