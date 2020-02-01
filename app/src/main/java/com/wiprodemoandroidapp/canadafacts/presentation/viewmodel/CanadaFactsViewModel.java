package com.wiprodemoandroidapp.canadafacts.presentation.viewmodel;

import com.wiprodemoandroidapp.canadafacts.domain.interactor.CanadaFactsInteractorContract;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import io.reactivex.Single;
import javax.inject.Inject;

public class CanadaFactsViewModel implements CanadaFactsViewModelContract {

  private CanadaFactsInteractorContract canadaFactsInteractor;

  @Inject
  public CanadaFactsViewModel(CanadaFactsInteractorContract canadaFactsInteractor) {
    this.canadaFactsInteractor = canadaFactsInteractor;
  }

  @Override
  public Single<CanadaFactsApiResponse> getCanadaFactsDetails() {
    return canadaFactsInteractor.getCanadaFactsDetails();
  }
}
