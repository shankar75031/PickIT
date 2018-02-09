package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pickit.kronos.pickit.Adapters.CategoryGridItemAdapter;
import com.pickit.kronos.pickit.Data.Constants;
import com.pickit.kronos.pickit.Objects.CategoryGridItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mItemCategoryGridView;
    private String airportTerminalCode;
    int selectedCategoryPosition;
    String selectedCategory;
    CategoryGridItemAdapter adapter;
    SharedPreferences pref;
    ArrayList<CategoryGridItem> categoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        setTitle(getResources().getString(R.string.app_name));
        mItemCategoryGridView = (GridView) findViewById(R.id.item_category_grid);
        pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        airportTerminalCode = pref.getString("airport_terminal", "");
        int id = getResources().getIdentifier(airportTerminalCode, "array", getPackageName());
        categoryArrayList = new ArrayList<CategoryGridItem>();


        pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        String airportCode = pref.getString("airport_terminal", "").substring(0,3);
        String terminalNumber = pref.getString("airport_terminal", "").substring(3);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/" + airportCode + "/" + terminalNumber);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(adapter != null)
                    categoryArrayList.clear();
                adapter.clear();
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    String post = postSnapshot.getKey();
                    CategoryGridItem categoryGridItem = new CategoryGridItem(R.drawable.shopping_bag, post);
                    categoryArrayList.add(categoryGridItem);
                }
                adapter = new CategoryGridItemAdapter( MainActivity.this, categoryArrayList);
                mItemCategoryGridView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: " ,firebaseError.getMessage());
            }
        });
         adapter = new CategoryGridItemAdapter
                (this, categoryArrayList);
        mItemCategoryGridView.setAdapter(adapter);

        mItemCategoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategoryPosition = i;

                TextView text = (TextView) view.findViewById(R.id.grid_item_category_name);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("category", text.getText().toString());
                editor.apply();
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {

            // Do something
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
