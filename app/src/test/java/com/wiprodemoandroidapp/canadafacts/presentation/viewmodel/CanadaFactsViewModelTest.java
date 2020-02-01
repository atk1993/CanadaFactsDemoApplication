package com.wiprodemoandroidapp.canadafacts.presentation.viewmodel;

import static org.mockito.MockitoAnnotations.initMocks;

import com.wiprodemoandroidapp.canadafacts.domain.interactor.CanadaFactsInteractorContract;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CanadaFactsViewModelTest {

  private CanadaFactsViewModel canadaFactsViewModel;
  @Mock CanadaFactsInteractorContract canadaFactsInteractor;
  @Mock CanadaFactsApiResponse canadaFactsApiResponse;

  @Before
  public void setUp() {
    initMocks(this);
    canadaFactsViewModel=new CanadaFactsViewModel(canadaFactsInteractor);
    
  }

  @Test
  public void getCanadaFactsDetails() {
    Mockito.when(canadaFactsInteractor.getCanadaFactsDetails())
        .thenReturn(Single.just(canadaFactsApiResponse));
    canadaFactsViewModel.getCanadaFactsDetails().test().hasSubscription();
  }
}
