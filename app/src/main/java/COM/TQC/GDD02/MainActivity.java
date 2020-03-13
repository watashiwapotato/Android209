package COM.TQC.GDD02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.net.Inet4Address;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "HIPPO_DEBUG";
    private DavidDataBase appDataBase;
    private RestaurantListAdapter restaurantListAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recyclerView = findViewById(R.id.main_recyclerView1);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        restaurantListAdapter = new RestaurantListAdapter(MainActivity.this);
        appDataBase = DavidDataBase.getsInstance(getApplicationContext());
        recyclerView.setAdapter(restaurantListAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Swipe to delete
                // 5. TO-DO
                appDataBase.placeDao().deletePlaces(restaurantListAdapter.getPlaces().get(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        getPlaces("onCreate");
    }

    private void getPlaces(final String str) {
        // 3. TO-DO
        MainViewModel mainViewModel = new MainViewModel(getApplication());
        mainViewModel.getPlaceList().observe(this, new Observer<List<Places>>() {
            @Override
            public void onChanged(List<Places> places) {
                restaurantListAdapter.setPlaces(places);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, Menu.FIRST, Menu.FIRST, MainActivity.this.getResources().getString(R.string.menu_edit));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                // 1. TO-DO
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
