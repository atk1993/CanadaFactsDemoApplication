package com.wiprodemoandroidapp.canadafacts.model.service;

import io.reactivex.Single;

public interface CanadaFactsServiceContract {

  /**
   * Subscribe to it in order to get {@link CanadaFactsApiResponse}.
   *
   * @return A {@link Single} emitting {@link CanadaFactsApiResponse}.
   */
  Single<CanadaFactsApiResponse> getCanadaFactsDetails();
}
