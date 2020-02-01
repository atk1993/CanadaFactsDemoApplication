package com.wiprodemoandroidapp.canadafacts.domain.interactor;

import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import io.reactivex.Single;

public interface CanadaFactsInteractorContract {
  
  /**
   * Subscribe to it in order to get {@link CanadaFactsApiResponse}.
   *
   * @return A {@link Single} emitting {@link CanadaFactsApiResponse}.
   */
  Single<CanadaFactsApiResponse> getCanadaFactsDetails();
  
}
