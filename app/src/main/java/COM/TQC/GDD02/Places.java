package COM.TQC.GDD02;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "places")
public class Places
{
  @PrimaryKey(autoGenerate = true)
  private int id;

  private String description;
  public int type;
  @ColumnInfo(name = "updated_at")
  private Date updatedAt;

  public Places(String description, int type, Date updatedAt)
  {
    this.description = description;
    this.type = type;
    this.updatedAt = updatedAt;
  }

  public int getId()
  {
    return id;
  }

  public String getDescription()
  {
    return description;
  }

  public int getMealType()
  {
    return type;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setMealType(int mealType)
  {
    this.type = mealType;
  }

  public void setUpdatedAt(Date updatedAt)
  {
    this.updatedAt = updatedAt;
  }
}
