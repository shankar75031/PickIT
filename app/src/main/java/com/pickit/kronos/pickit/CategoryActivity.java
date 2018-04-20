package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pickit.kronos.pickit.Adapters.ItemGridItemAdapter;
import com.pickit.kronos.pickit.Data.Constants;
import com.pickit.kronos.pickit.Objects.Item;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<Item> itemList;
    GridView gridView;
    ItemGridItemAdapter itemGridItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        FirebaseApp.initializeApp(this);
        itemList = new ArrayList<Item>();
        gridView = findViewById(R.id.grid_view_items);

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        String departureAirport = pref.getString("DepartureAirport", "");
        String category = pref.getString("category", "");
        setTitle(category);
        //Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/" + departureAirport + "/" + category);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                itemList.clear();
                if (itemGridItemAdapter != null)
                    itemGridItemAdapter.clear();
                Log.e("Count ", "" + snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if (!postSnapshot.getKey().equals("image") && !postSnapshot.getKey().equals("carts")) {
                        Item post = postSnapshot.getValue(Item.class);
                        itemList.add(post);
                    }
                }
                itemGridItemAdapter = new ItemGridItemAdapter(CategoryActivity.this, itemList);
                gridView.setAdapter(itemGridItemAdapter);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: ", firebaseError.getMessage());
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(CategoryActivity.this, PurchaseActivity.class).
                        putExtra("itemName", itemList.get(i).getItemName())
                        .putExtra("Price", itemList.get(i).getPrice())
                        .putExtra("Description", itemList.get(i).getDescription())
                        .putExtra("qty", 1)
                        .putExtra("Image", itemList.get(i).getImage())
                );

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

            startActivity(new Intent(CategoryActivity.this, CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
