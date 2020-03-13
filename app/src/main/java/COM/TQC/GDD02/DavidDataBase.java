package COM.TQC.GDD02;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Places.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DavidDataBase extends RoomDatabase
{
  public static final String TAG = "HIPPO_DEBUG";
  public static final Object LOCK = new Object();
  public static final String DATABASE_NAME = "restaurant";
  private static DavidDataBase sInstance;

  public static DavidDataBase getsInstance(Context context)
  {
    if (sInstance== null)
    {
      synchronized (LOCK)
      {
        // 2. TO-DO
          sInstance = Room.databaseBuilder(context,DavidDataBase.class,DATABASE_NAME).allowMainThreadQueries().build();
      }
    }
    Log.d(TAG,"getting the database instance:"+DATABASE_NAME);
    return sInstance;
  }

  public abstract PlaceDataAccessObject placeDao();

  @NonNull
  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config)
  {
    return null;
  }

  @NonNull
  @Override
  protected InvalidationTracker createInvalidationTracker()
  {
    return null;
  }

  @Override
  public void clearAllTables()
  {

  }
}
