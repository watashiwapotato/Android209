package COM.TQC.GDD02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;

public class EditActivity extends AppCompatActivity
{
  private String TAG = "HIPPO_DEBUG";
  public static final int TYPE_CHINESE = 1;
  public static final int TYPE_WESTERN = 2;
  public static final int TYPE_JAPANESE = 3;
  private Button buttonAdd;
  private EditText etPlaces;

  private RadioButton radioButtonHigh;
  private RadioButton radioButtonMedium;
  private RadioButton radioButtonLow;
  private DavidDataBase mdb;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);

    etPlaces = findViewById(R.id.activity_edit_editText1);
    buttonAdd = findViewById(R.id.activity_edit_saveButton);

    radioButtonHigh = findViewById(R.id.radButton1);
    radioButtonMedium = findViewById(R.id.radButton2);
    radioButtonLow = findViewById(R.id.radButton3);

    mdb = DavidDataBase.getsInstance(getApplicationContext());
    final Intent intent = getIntent();
    if(intent != null && intent.hasExtra(Constants.EXTRA_RESTAURANT_ID))
    {
      buttonAdd.setText(getResources().getString(R.string.update_button));
      final LiveData<Places> places = mdb.placeDao().getPlacesById(intent.getIntExtra(Constants.EXTRA_RESTAURANT_ID, 0));
      EditViewModel viewModel = new EditViewModel(mdb, intent.getIntExtra(Constants.EXTRA_RESTAURANT_ID, 0));
      viewModel.getPlaces().observe(EditActivity.this, new Observer<Places>()
      {
        @Override
        public void onChanged(@Nullable Places place)
        {
          final int typeOfRestaurant = place.getMealType();
          etPlaces.setText(place.getDescription());
          Toast.makeText(EditActivity.this, place.getDescription().toString(), Toast.LENGTH_SHORT).show();
          setRestaurantType(typeOfRestaurant);
        }
      });
    }
    else
    {
      buttonAdd.setText(getResources().getString(R.string.add_button));
    }

    buttonAdd.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        String text = etPlaces.getText().toString().trim();
        int typeOfRestaurant = getRestauranTypetFromViews();
        Date date = new Date();
        final Places place = new Places(text, typeOfRestaurant, date);
        DavidExecutor.getInstance().diskIO().execute(new Runnable()
        {
          @Override
          public void run()
          {
            if(intent != null && intent.hasExtra(Constants.EXTRA_RESTAURANT_ID))
            {
              // 4. TO-DO: 更新資料庫
            }
            else
            {
              // 4. TO-DO: 新增至資料庫
            }
            finish();
          }
        });
      }
    });
  }/*End of onCreate()*/

  public int getRestauranTypetFromViews()
  {
    int typeOfRestaurant = 1;
    int checkedId = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
    switch(checkedId)
    {
      case R.id.radButton1:
        typeOfRestaurant = TYPE_CHINESE;
        break;
      case R.id.radButton2:
        typeOfRestaurant = TYPE_WESTERN;
        break;
      case R.id.radButton3:
        typeOfRestaurant = TYPE_JAPANESE;
    }
    return typeOfRestaurant;
  }

  public void setRestaurantType(int typeOfRestaurant)
  {
    switch(typeOfRestaurant)
    {
      case TYPE_CHINESE:
        radioButtonHigh.setChecked(true);
        break;
      case TYPE_WESTERN:
        radioButtonMedium.setChecked(true);
        break;
      case TYPE_JAPANESE:
        radioButtonLow.setChecked(true);
        break;
    }
  }
}
