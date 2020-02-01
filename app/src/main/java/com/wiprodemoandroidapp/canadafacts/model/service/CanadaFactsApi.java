package com.wiprodemoandroidapp.canadafacts.model.service;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CanadaFactsApi {

  @GET("facts.json")
  Single<CanadaFactsApiResponse> getCanadaFactsDetails();
}
