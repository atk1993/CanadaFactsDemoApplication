package com.wiprodemoandroidapp.canadafacts.di;

import com.wiprodemoandroidapp.canadafacts.domain.interactor.CanadaFactsInteractorContract;
import com.wiprodemoandroidapp.canadafacts.presentation.viewmodel.CanadaFactsViewModel;
import com.wiprodemoandroidapp.canadafacts.presentation.viewmodel.CanadaFactsViewModelContract;
import com.wiprodemoandroidapp.di.annotations.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class CanadaFactsViewModule {

  @ActivityScope
  @Provides
  CanadaFactsViewModelContract provideCanadaFactsViewModel(
      CanadaFactsInteractorContract canadaFactsInteractor) {
    return new CanadaFactsViewModel(canadaFactsInteractor);
  }
}
