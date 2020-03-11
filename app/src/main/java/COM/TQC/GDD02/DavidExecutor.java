package COM.TQC.GDD02;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DavidExecutor
{
  private static final Object LOCK = new Object();
  private static DavidExecutor sInstance;
  private final Executor diskIO;

  private DavidExecutor(Executor diskIO)
  {
    this.diskIO = diskIO;
  }

  public static DavidExecutor getInstance()
  {
    if(sInstance == null)
    {
      synchronized(LOCK)
      {
        sInstance = new DavidExecutor(Executors.newSingleThreadExecutor());
      }
    }
    return sInstance;
  }

  public Executor diskIO()
  {
    return diskIO;
  }
}
