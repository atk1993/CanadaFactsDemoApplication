package com.wiprodemoandroidapp.canadafacts.domain.interactor;

import static org.mockito.MockitoAnnotations.initMocks;

import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsServiceContract;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CanadaFactsInteractorTest {

  private CanadaFactsInteractor canadaFactsInteractor;
  @Mock CanadaFactsServiceContract canadaFactsServiceContract;
  @Mock CanadaFactsApiResponse canadaFactsApiResponse;

  @Before
  public void setUp() {
    initMocks(this);
    canadaFactsInteractor = new CanadaFactsInteractor(canadaFactsServiceContract);
  }

  @Test
  public void getCanadaFactsDetails_Check_For_Subscription() {
    Mockito.when(canadaFactsServiceContract.getCanadaFactsDetails())
        .thenReturn(Single.just(canadaFactsApiResponse));
    canadaFactsInteractor.getCanadaFactsDetails().test().hasSubscription();
  }
}
