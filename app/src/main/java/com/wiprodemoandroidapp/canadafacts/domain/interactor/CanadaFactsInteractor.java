package com.wiprodemoandroidapp.canadafacts.domain.interactor;

import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsServiceContract;
import io.reactivex.Single;
import javax.inject.Inject;

public class CanadaFactsInteractor implements CanadaFactsInteractorContract {

  private CanadaFactsServiceContract canadaFactsService;

  @Inject
  public CanadaFactsInteractor(CanadaFactsServiceContract canadaFactsService) {
    this.canadaFactsService = canadaFactsService;
  }

  @Override
  public Single<CanadaFactsApiResponse> getCanadaFactsDetails() {
    return canadaFactsService.getCanadaFactsDetails();
  }
}
