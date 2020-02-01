package com.wiprodemoandroidapp.canadafacts.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import com.wiprodemoandroidapp.R;
import com.wiprodemoandroidapp.canadafacts.model.service.CanadaFactsApiResponse;
import com.wiprodemoandroidapp.canadafacts.model.service.Row;
import java.util.List;

class CanadaFactsDetailsRecyclerViewAdapter
    extends RecyclerView.Adapter<
        CanadaFactsDetailsRecyclerViewAdapter.CanadaFactsDetailsViewHolder> {
  private CanadaFactsApiResponse canadaFactsApiResponse;

  CanadaFactsDetailsRecyclerViewAdapter(CanadaFactsApiResponse canadaFactsApiResponse) {
    this.canadaFactsApiResponse = canadaFactsApiResponse;
  }

  @NonNull
  @Override
  public CanadaFactsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.layout_canada_facts_details_list_item, parent, false);
    return new CanadaFactsDetailsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CanadaFactsDetailsViewHolder holder, int position) {

    Row rowItem = canadaFactsApiResponse.getRows().get(position);

    holder.canadaFactsDetailsTitleItem.setText(rowItem.getRowTitle());
    holder.canadaFactsDetailsDescriptionItem.setText(rowItem.getDescription());
    if (rowItem.getImageHref() != null) {
      Picasso.get()
          .load(rowItem.getImageHref())
          .placeholder(R.drawable.icon_image_placeholder)
          .error(R.drawable.icon_image_error)
          .into(holder.canadaFactsDetailsImageItem);
    }
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount() {

    if (!canadaFactsApiResponse.getRows().isEmpty()) {
      return canadaFactsApiResponse.getRows().size();
    } else {
      return 0;
    }
  }

  class CanadaFactsDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.canadaFactsDetailsTitleItem)
    AppCompatTextView canadaFactsDetailsTitleItem;

    @BindView(R.id.canadaFactsDetailsDescriptionItem)
    AppCompatTextView canadaFactsDetailsDescriptionItem;

    @BindView(R.id.canadaFactsDetailsImageItem)
    AppCompatImageView canadaFactsDetailsImageItem;

    @BindView(R.id.rootItemLayout)
    CardView rootItemLayout;

    public CanadaFactsDetailsViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  // Clean all elements of the recycler
  public void clear() {
    canadaFactsApiResponse.getRows().clear();
    notifyDataSetChanged();
  }

  // Add a list of items -- change to type used
  public void addAll(List<Row> list) {
    canadaFactsApiResponse.getRows().addAll(list);
    notifyDataSetChanged();
  }
}
