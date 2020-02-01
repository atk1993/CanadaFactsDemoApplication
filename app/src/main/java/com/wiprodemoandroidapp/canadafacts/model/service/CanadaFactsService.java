package com.wiprodemoandroidapp.canadafacts.model.service;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class CanadaFactsService implements CanadaFactsServiceContract {

  private CanadaFactsApi canadaFactsApi;

  @Inject
  public CanadaFactsService(CanadaFactsApi canadaFactsApi) {
    this.canadaFactsApi = canadaFactsApi;
  }

  /**
   * Subscribe to it in order to get {@link CanadaFactsApiResponse}.
   *
   * @return A {@link Single} emitting {@link CanadaFactsApiResponse}.
   */
  @Override
  public Single<CanadaFactsApiResponse> getCanadaFactsDetails() {
    return canadaFactsApi
        .getCanadaFactsDetails()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    //        .onErrorResumeNext(this::parseError);
  }
}
