package COM.TQC.GDD02;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.PlaceViewHolder>
{
  private static final String DATE_FORMAT = "yyyy/MM/dd HH:MM:SS";
  private List<Places> mPlaceEntries;
  private Context mContext;

  private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

  public RestaurantListAdapter(Context context)
  {
    mContext = context;
  }

  @Override
  public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View view = LayoutInflater.from(mContext) .inflate(R.layout.layout_row_item, parent, false);
    return new PlaceViewHolder(view);
  }

  @Override
  public void onBindViewHolder(PlaceViewHolder holder, int position)
  {
    Places placeEntry = mPlaceEntries.get(position);
    String description = placeEntry.getDescription();
    int intType = placeEntry.getMealType();

    final int id = placeEntry.getId();
    String updatedAt = mContext.getResources().getString(R.string.str_update_at)+dateFormat.format(placeEntry.getUpdatedAt());
    holder.placeDescriptionView.setText(description);
    holder.updatedAtView.setText(updatedAt);
    String typeString = "";
    switch(intType)
    {
      case 1:
        typeString = mContext.getResources().getString(R.string.res_type_chinese);
        break;
      case 2:
        typeString = mContext.getResources().getString(R.string.res_type_western);
        break;
      case 3:
        typeString = mContext.getResources().getString(R.string.res_type_japanese);
        break;
    }

    holder.typeView.setText(typeString);

    GradientDrawable priorityCircle = (GradientDrawable) holder.typeView.getBackground();
    int typeColor = getTypeColor(intType);
    priorityCircle.setColor(typeColor);

    holder.view.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        Intent intent = new Intent(mContext, EditActivity.class);
        intent.putExtra(Constants.EXTRA_RESTAURANT_ID, id);
        mContext.startActivity(intent);
      }
    });
  }

  private int getTypeColor(int type)
  {
    int typeColor = 0;

    switch(type)
    {
      case 1:
        typeColor = ContextCompat.getColor(mContext, R.color.materialBlue);
        break;
      case 2:
        typeColor = ContextCompat.getColor(mContext, R.color.materialGreen);
        break;
      case 3:
        typeColor = ContextCompat.getColor(mContext, R.color.materialYellow);
        break;
      default:
        break;
    }
    return typeColor;
  }

  @Override
  public int getItemCount()
  {
    if(mPlaceEntries == null)
    {
      return 0;
    }
    return mPlaceEntries.size();
  }

  public void setPlaces(List<Places> placeEntries)
  {
    mPlaceEntries = placeEntries;
    notifyDataSetChanged();
  }

  public List<Places> getPlaces()
  {
    return mPlaceEntries;
  }

  class PlaceViewHolder extends RecyclerView.ViewHolder
  {
    TextView placeDescriptionView;
    TextView updatedAtView;
    TextView typeView;
    View view;

    public PlaceViewHolder(View itemView)
    {
      super(itemView);
      placeDescriptionView = itemView.findViewById(R.id.row_item_placeDescription);
      updatedAtView = itemView.findViewById(R.id.row_item_placeUpdatedAt);
      typeView = itemView.findViewById(R.id.row_item_place_typeTextView);
      view = itemView;
    }
  }
}
