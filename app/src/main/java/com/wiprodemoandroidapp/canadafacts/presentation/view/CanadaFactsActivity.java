package com.wiprodemoandroidapp.canadafacts.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.snackbar.Snackbar;
import com.wiprodemoandroidapp.CanadaFactsApplication;
import com.wiprodemoandroidapp.R;
import com.wiprodemoandroidapp.canadafacts.di.CanadaFactsViewModule;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import com.wiprodemoandroidapp.canadafacts.model.service.Row;
import com.wiprodemoandroidapp.canadafacts.presentation.viewmodel.CanadaFactsViewModelContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import javax.inject.Inject;

public class CanadaFactsActivity extends AppCompatActivity implements OnRefreshListener {

  @BindView(R.id.swiperefresh)
  SwipeRefreshLayout swipeRefresh;

  @BindView(R.id.recyclerView)
  RecyclerView canadaFactsDetailsRecyclerView;

  @BindView(R.id.progress_bar)
  ProgressBar progressBar;

  @Inject CanadaFactsViewModelContract canadaFactsViewModel;

  private CanadaFactsDetailsRecyclerViewAdapter canadaFactsDetailsRecyclerViewAdapter;
  private List<Row> canadaFactsDetailsRowItemsList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_canad_facts_details);
    CanadaFactsApplication.get(this)
        .getAppComponent()
        .plus(new CanadaFactsViewModule())
        .inject(this);
    ButterKnife.bind(this);
    subscribeToGetCanadaFactsDetails();
    setListenerToSwipeGesture();
  }

  private void setListenerToSwipeGesture() {
    swipeRefresh.setOnRefreshListener(this);
  }

  private void subscribeToGetCanadaFactsDetails() {
    showProgressBar();
    canadaFactsViewModel
        .getCanadaFactsDetails()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new DisposableSingleObserver<CanadaFactsApiResponse>() {
              @Override
              public void onSuccess(CanadaFactsApiResponse canadaFactsApiResponse) {
                dismissProgressBar();
                handleCanadaFactsApiSuccessResponse(canadaFactsApiResponse);
              }

              @Override
              public void onError(Throwable e) {
                dismissProgressBar();
                Log.e("CanadaFactsApi Error", e.toString());
                Snackbar.make(canadaFactsDetailsRecyclerView, e.toString(), Snackbar.LENGTH_SHORT)
                    .show();
              }
            });
  }

  private void handleCanadaFactsApiSuccessResponse(CanadaFactsApiResponse canadaFactsApiResponse) {
    if (canadaFactsApiResponse != null) {
      if (canadaFactsApiResponse.getRows() != null) {
        canadaFactsDetailsRowItemsList = canadaFactsApiResponse.getRows();
        setDataToCanadaFactsDetailsList(canadaFactsApiResponse);
      }
    } else {
      Snackbar.make(
              canadaFactsDetailsRecyclerView,
              this.getString(R.string.text_api_error),
              Snackbar.LENGTH_SHORT)
          .show();
    }
  }

  private void setDataToCanadaFactsDetailsList(CanadaFactsApiResponse canadaFactsApiResponse) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(canadaFactsApiResponse.getTitle());
    }
    canadaFactsDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    canadaFactsDetailsRecyclerViewAdapter =
        new CanadaFactsDetailsRecyclerViewAdapter(canadaFactsApiResponse);
    canadaFactsDetailsRecyclerView.setAdapter(canadaFactsDetailsRecyclerViewAdapter);
  }

  /** Show Progress Bar. */
  public void showProgressBar() {
    if (progressBar != null) {
      progressBar.setVisibility(View.VISIBLE);
    } else {
      Log.e("Show Progress Bar Error", this.getString(R.string.text_progressbar_error));
    }
  }

  /** Hide Progress Bar. */
  public void dismissProgressBar() {
    try {
      if (progressBar != null) {
        progressBar.setVisibility(View.GONE);
      }
    } catch (Exception ex) {
      Log.e("Hide Progress Bar Error", this.getString(R.string.text_progressbar_error));
    }
  }

  /** Called when a swipe gesture triggers a refresh. */
  @Override
  public void onRefresh() {
    subscribeToGetCanadaFactsDetails();
    canadaFactsDetailsRecyclerViewAdapter.clear();
    canadaFactsDetailsRecyclerViewAdapter.addAll(canadaFactsDetailsRowItemsList);
    swipeRefresh.setRefreshing(false);
  }
}
