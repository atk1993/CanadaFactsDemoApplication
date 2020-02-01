package com.wiprodemoandroidapp.canadafacts.model.service;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/** Used to parse the Canada Facts Api response. */
public class CanadaFactsApiResponse {

  @SerializedName("title")
  private String title;

  @SerializedName("rows")
  private List<Row> rows = null;

  public String getTitle() {
    return title;
  }

  public List<Row> getRows() {
    return rows;
  }
}
