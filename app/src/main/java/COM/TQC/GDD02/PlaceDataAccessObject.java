package COM.TQC.GDD02;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlaceDataAccessObject
{
  // 1. TO-DO
  LiveData<List<Places>> loadAllPlaces();

  // 1. TO-DO
  void insertPlaces(Places place);

  // 1. TO-DO
  void updatePlaces(Places place);

  // 1. TO-DO
  void deletePlaces(Places place);

  // 1. TO-DO
  LiveData<Places> getPlacesById(int id);

}
