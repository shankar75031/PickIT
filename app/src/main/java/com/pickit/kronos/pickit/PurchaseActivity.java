package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pickit.kronos.pickit.Data.Constants;
import com.pickit.kronos.pickit.Objects.CartItem;

import java.util.Random;

public class PurchaseActivity extends AppCompatActivity {

    String itemName;
    String price;
    int qty;
    String image;
    private TextView itemNameTextView;
    private TextView priceTextView;
    private TextView descTextView;
    private TextView qtyTextView;
    private ImageView imageView;
    private Button incQty;
    private Button decQty;
    private Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Bundle extras = getIntent().getExtras();
        itemName = extras.getString("itemName");
        price = extras.getString("Price");
        image = extras.getString("Image");
        String desc = extras.getString("Description");
        FirebaseApp.initializeApp(this);
        qty = extras.getInt("qty");

        itemNameTextView = findViewById(R.id.itemname_display);
        priceTextView = findViewById(R.id.dynam_price);
        descTextView = findViewById(R.id.dynam_desc);
        qtyTextView = findViewById(R.id.dynam_quantity);
        incQty = findViewById(R.id.increase);
        decQty = findViewById(R.id.decrease);
        addToCart = findViewById(R.id.button3);
        imageView = findViewById(R.id.itemimage_display);

        itemNameTextView.setText(itemName);
        priceTextView.setText(price);
        descTextView.setText(desc);
        Glide.with(imageView.getContext())
                .load(image)
                .into(imageView);
        qtyTextView.setText(String.valueOf(qty));
        incQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(qtyTextView.getText().toString());
                if (temp <= 9) {
                    temp++;
                } else {
                    Toast.makeText(getApplicationContext(), "Maximum Limit is 10", Toast.LENGTH_SHORT).show();
                }

                qtyTextView.setText(String.valueOf(temp));
            }
        });
        decQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(qtyTextView.getText().toString());
                if (temp > 1) {
                    temp--;
                } else {
                    Toast.makeText(getApplicationContext(), "Minimum Limit is 1", Toast.LENGTH_SHORT).show();
                }
                qtyTextView.setText(String.valueOf(temp));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);

                String airportCode = pref.getString("DepartureAirport", "");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/" + airportCode + "/carts");
                qty = Integer.parseInt(qtyTextView.getText().toString());
                CartItem cartItem = new CartItem(image, itemName, price, String.valueOf(qty));
                myRef.child("cart").push().setValue(cartItem);
                Toast.makeText(PurchaseActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
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

            startActivity(new Intent(PurchaseActivity.this, CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
