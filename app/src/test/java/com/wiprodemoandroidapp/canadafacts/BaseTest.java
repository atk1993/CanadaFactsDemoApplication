package com.wiprodemoandroidapp.canadafacts;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;
import org.junit.Before;

public class BaseTest {

  private static TestScheduler testScheduler = new TestScheduler();

  @Before
  public void setupRxScheduler() {
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> testScheduler);
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> testScheduler);
  }

  protected void triggerActions() {
    testScheduler.triggerActions();
  }
}
