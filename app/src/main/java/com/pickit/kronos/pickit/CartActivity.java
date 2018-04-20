package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pickit.kronos.pickit.Adapters.CartItemAdapter;
import com.pickit.kronos.pickit.Data.Constants;
import com.pickit.kronos.pickit.Objects.CartItem;
import com.pickit.kronos.pickit.Objects.Item;

import java.util.ArrayList;
import java.util.Random;

public class CartActivity extends AppCompatActivity {

    ListView cartList;
    CartItemAdapter adapter;
    SharedPreferences pref;
    ArrayList<CartItem> categoryArrayList;
private FloatingActionButton checkoutButton;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Cart");
        cartList = (ListView) findViewById(R.id.cart_list);
        checkoutButton = (FloatingActionButton) findViewById(R.id.checkout_button);
        pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
        String departureAirport = pref.getString("DepartureAirport", "");

        //Initialized firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference("/" + departureAirport + "/carts/");
        categoryArrayList = new ArrayList<CartItem>();

        adapter = new CartItemAdapter(this, categoryArrayList);
        adapter.setNotifyOnChange(true);
        attachDatabaseReadListner();
        cartList.setAdapter(adapter);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int pickedNumber = rand.nextInt((9999 - 1000) + 1) + 1000;
                startActivity(new Intent(CartActivity.this,ThankYouActivity.class).putExtra("pickITCode",pickedNumber));
            }
        });
    }
    //This method will attach the listener on database reference object
    private void attachDatabaseReadListner() {
        if (mChildEventListner == null) {
            mChildEventListner = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    //Reading a newly added object to the database and adding it to the adapter of the list
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                        CartItem item = postSnapshot.getValue(CartItem.class);
                        adapter.add(item);
                    }
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
