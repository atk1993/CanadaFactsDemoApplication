package com.wiprodemoandroidapp.canadafacts.di;

import com.wiprodemoandroidapp.canadafacts.domain.interactor.CanadaFactsInteractor;
import com.wiprodemoandroidapp.canadafacts.domain.interactor.CanadaFactsInteractorContract;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsServiceContract;
import com.wiprodemoandroidapp.di.annotations.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class CanadaFactsDomainModule {

  @ActivityScope
  @Provides
  CanadaFactsInteractorContract provideCanadaFactsInteractor(
      CanadaFactsServiceContract canadaFactsService) {
    return new CanadaFactsInteractor(canadaFactsService);
  }
}
