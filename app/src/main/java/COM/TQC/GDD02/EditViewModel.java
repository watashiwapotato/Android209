package COM.TQC.GDD02;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class EditViewModel extends ViewModel
{
  private LiveData<Places> place;
  private String TAG = "HIPPO_DEBUG";

  public EditViewModel(DavidDataBase appDataBase, int id)
  {
    place = appDataBase.placeDao().getPlacesById(id);
    Log.i(TAG," EditViewModel: Loading place");
  }

  public LiveData<Places> getPlaces()
  {
    return place;
  }
}
