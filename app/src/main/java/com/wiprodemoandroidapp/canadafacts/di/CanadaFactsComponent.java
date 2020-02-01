package com.wiprodemoandroidapp.canadafacts.di;

import com.wiprodemoandroidapp.canadafacts.presentation.view.CanadaFactsActivity;
import com.wiprodemoandroidapp.di.annotations.ActivityScope;
import dagger.Subcomponent;

/** Represents by the dagger to resolve the dependency related to the Activity Level. */
@ActivityScope
@Subcomponent(
    modules = {
      CanadaFactsViewModule.class,
      CanadaFactsModelModule.class,
      CanadaFactsDomainModule.class
    })
public interface CanadaFactsComponent {

  void inject(CanadaFactsActivity activity);
}
