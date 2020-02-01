package com.wiprodemoandroidapp.canadafacts.di;

import static com.wiprodemoandroidapp.di.module.CanadaFactsNetworkModule.CANADA_FACTS_RETROFIT;

import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApi;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsService;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsServiceContract;
import com.wiprodemoandroidapp.di.annotations.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import retrofit2.Retrofit;

@Module
public class CanadaFactsModelModule {
  
  
  @ActivityScope
  @Provides
  CanadaFactsApi provideCanadaFactsApi(@Named(CANADA_FACTS_RETROFIT) Retrofit retrofit) {
    return retrofit.create(CanadaFactsApi.class);
  }
  
  @ActivityScope
  @Provides
  CanadaFactsServiceContract provideCanadaFactsSerivce(CanadaFactsApi canadaFactsApi) {
    return new CanadaFactsService(canadaFactsApi);
  }
}
