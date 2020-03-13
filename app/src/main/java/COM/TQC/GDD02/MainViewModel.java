package COM.TQC.GDD02;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {
    private String TAG = "HIPPO_DEBUG";
    private LiveData<List<Places>> placeList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        // 3. TO-DO
        DavidDataBase davidDataBase =DavidDataBase.getsInstance(getApplication().getApplicationContext());
        placeList = davidDataBase.placeDao().loadAllPlaces();
    }

    public LiveData<List<Places>> getPlaceList() {
        return placeList;
    }

}
