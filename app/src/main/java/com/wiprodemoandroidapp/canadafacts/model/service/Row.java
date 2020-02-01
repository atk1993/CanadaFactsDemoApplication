package com.wiprodemoandroidapp.canadafacts.model.service;

import com.google.gson.annotations.SerializedName;

public class Row {

  @SerializedName("title")
  private String rowTitle;

  @SerializedName("description")
  private String description;

  @SerializedName("imageHref")
  private String imageHref;

  public String getRowTitle() {
    return rowTitle;
  }

  public String getDescription() {
    return description;
  }

  public String getImageHref() {
    return imageHref;
  }
}
