package com.wiprodemoandroidapp.canadafacts.model.service;

import static org.mockito.MockitoAnnotations.initMocks;

import com.wiprodemoandroidapp.canadafacts.BaseTest;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CanadaFactsServiceTest extends BaseTest{

  private CanadaFactsService canadaFactsService;
  @Mock CanadaFactsApi canadaFactsApi;
  @Mock CanadaFactsApiResponse canadaFactsApiResponse;

  @Before
  public void setUp() {
    initMocks(this);
    canadaFactsService = new CanadaFactsService(canadaFactsApi);
  }

  @Test
  public void getCanadaFactsDetails_Check_For_Not_Null() {
    Mockito.when(canadaFactsApi.getCanadaFactsDetails()).thenReturn(Single.just(canadaFactsApiResponse));
  
    Single<CanadaFactsApiResponse> observable = canadaFactsService.getCanadaFactsDetails();
    TestObserver<CanadaFactsApiResponse> canadaFactsApiResponseTestObserver = TestObserver.create();
    observable.subscribe(canadaFactsApiResponseTestObserver);
    triggerActions();
    canadaFactsApiResponseTestObserver.assertValue(canadaFactsApiResponse);
  
    Mockito.verify(canadaFactsApi, Mockito.times(1)).getCanadaFactsDetails();
    
  }
}
