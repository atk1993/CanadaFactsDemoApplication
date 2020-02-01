package com.wiprodemoandroidapp.di.component;

import com.wiprodemoandroidapp.CanadaFactsApplication;
import com.wiprodemoandroidapp.canadafacts.di.CanadaFactsComponent;
import com.wiprodemoandroidapp.canadafacts.di.CanadaFactsViewModule;
import com.wiprodemoandroidapp.di.annotations.ApplicationScope;
import com.wiprodemoandroidapp.di.module.ApiUrlsModule;
import com.wiprodemoandroidapp.di.module.ApplicationModule;
import com.wiprodemoandroidapp.di.module.CanadaFactsNetworkModule;
import com.wiprodemoandroidapp.di.module.NetworkModule;
import dagger.Component;

/**
 * Represents the Application level Dagger component. Method exposed over here can be used across
 * application.
 */
@ApplicationScope
@Component(modules = {ApplicationModule.class, ApiUrlsModule.class, NetworkModule.class,
    CanadaFactsNetworkModule.class})
public interface AppComponent {

  void inject(CanadaFactsApplication application);

  CanadaFactsComponent plus(CanadaFactsViewModule viewModule);
}
