package com.wiprodemoandroidapp;

import android.app.Application;
import android.content.Context;
import com.wiprodemoandroidapp.di.component.AppComponent;
import com.wiprodemoandroidapp.di.component.DaggerAppComponent;
import com.wiprodemoandroidapp.di.module.ApplicationModule;
import com.wiprodemoandroidapp.di.module.NetworkModule;

public class CanadaFactsApplication extends Application implements AppContentProvider {

  private AppComponent appComponent;
  
  @Override
  public void onCreate() {
    super.onCreate();

    // Init the dagger component
    initDaggerAppComponent();
  }

  public static CanadaFactsApplication get(Context context) {
    return (CanadaFactsApplication) context.getApplicationContext();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public Context getContext() {
    return this;
  }

  /** init the application level Dagger component. */
  private void initDaggerAppComponent() {
    appComponent =
        DaggerAppComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .networkModule(new NetworkModule())
            .build();

    appComponent.inject(this);
  }
}
