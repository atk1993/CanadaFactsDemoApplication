package com.wiprodemoandroidapp.canadafacts.presentation.viewmodel;

import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import io.reactivex.Single;

/**
 * It is responsible for providing the Canada Facts to the views. The objects passed to the views is
 * containing the information required by the view.
 */
public interface CanadaFactsViewModelContract {
  
  /**
   * Subscribe to it in order to get {@link CanadaFactsApiResponse}.
   *
   * @return A {@link Single} emitting {@link CanadaFactsApiResponse}.
   */
  Single<CanadaFactsApiResponse> getCanadaFactsDetails();
}
