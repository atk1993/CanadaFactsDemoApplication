package com.wiprodemoandroidapp.di.module;

import android.content.Context;
import com.am.env.EnvironmentConfig;
import com.am.env.EnvironmentConfigContract;
import com.wiprodemoandroidapp.CanadaFactsApplication;
import com.wiprodemoandroidapp.di.annotations.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/*
 * Provide the dependency requires by the login module.
 * Method exposed over here is applicable to the login Module only.
 */
@Module
public class ApplicationModule {

  private CanadaFactsApplication canadaFactsApplication;

  public ApplicationModule(CanadaFactsApplication canadaFactsApplication) {
    this.canadaFactsApplication = canadaFactsApplication;
  }

  @ApplicationScope
  @Provides
  Context providesApplicationContext() {
    return canadaFactsApplication.getApplicationContext();
  }

  @ApplicationScope
  @Provides
  EnvironmentConfigContract providesEnvironmentConfigContract(Context context) {
    return new EnvironmentConfig(context);
  }
}
