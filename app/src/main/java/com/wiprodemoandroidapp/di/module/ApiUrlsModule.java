package com.wiprodemoandroidapp.di.module;

import static com.wiprodemoandroidapp.di.module.UrlConstants.BASE_API_URL;

import com.am.env.EnvironmentConfigContract;
import com.wiprodemoandroidapp.di.annotations.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class ApiUrlsModule {
  @Provides
  @ApplicationScope
  EnvironmentAttributes providesEnvironmentAttributes(
      EnvironmentConfigContract environmentConfigContract) {
    return environmentConfigContract.getEnvironmentAttributes();
  }

  @Provides
  @ApplicationScope
  @Named(BASE_API_URL)
  String providesBaseApiUrl(EnvironmentAttributes environmentAttributes) {
    return environmentAttributes.getGateWayUrl();
  }

  @Provides
  @ApplicationScope
  @Named(UrlConstants.BASE_PATH)
  String providesBasePath(EnvironmentAttributes environmentAttributes) {
    return environmentAttributes.getBasePath();
  }

  @Provides
  @ApplicationScope
  @Named(UrlConstants.CANADA_FACTS_ENDPOINT)
  String providesCanadaFactsEndPoint(EnvironmentAttributes environmentAttributes) {
    return environmentAttributes.getCanadaFactsEndpoint();
  }
}
