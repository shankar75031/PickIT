package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int selectedCategoryPosition;
    String selectedCategory;
    SharedPreferences pref;
    ArrayList<CategoryGridItem> categoryArrayList;
    CategoryGridItemAdapter adapter;
    private GridView mItemCategoryGridView;
    private String airportTerminalCode;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        setTitle(getResources().getString(R.string.app_name));
        mItemCategoryGridView = findViewById(R.id.item_category_grid);
        pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        airportTerminalCode = pref.getString("DepartureAirport", "");
        categoryArrayList = new ArrayList<CategoryGridItem>();


        pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        String departureAirport = pref.getString("DepartureAirport", "");

        //TODO:Arrival and departrure airport price compare
        //String arrivalAirport = pref.getString("ArrivalAirport", "").substring(3);

        //Initialized firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference("/" + departureAirport + "/");

        adapter = new CategoryGridItemAdapter
                (this, categoryArrayList);
        adapter.setNotifyOnChange(true);

        mItemCategoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategoryPosition = i;

                TextView text = view.findViewById(R.id.grid_item_category_name);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("category", text.getText().toString());
                editor.apply();
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });
        attachDatabaseReadListner();
        mItemCategoryGridView.setAdapter(adapter);


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

    //This method will attach the listener on database reference object
    private void attachDatabaseReadListner() {
        if (mChildEventListner == null) {
            mChildEventListner = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Reading a newly added object to the database and adding it to the adapter of the list
                    CategoryGridItem categoryGridItem = new CategoryGridItem();
                    categoryGridItem.setGetCategoryName(dataSnapshot.getKey());
                    categoryGridItem.setCategoryImageId(dataSnapshot.child("image").getValue(String.class));
                    adapter.add(categoryGridItem);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            //Adding child event listner to the database reference
            mMessagesDatabaseReference.addChildEventListener(mChildEventListner);
        }
    }
}
