package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class PurchaseActivity extends AppCompatActivity {

    String itemName;
    String price;
    int qty;
    private TextView itemNameTextView;
    private TextView priceTextView;
    private TextView descTextView;
    private TextView qtyTextView;
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

        itemNameTextView.setText(itemName);
        priceTextView.setText(price);
        descTextView.setText(desc);
        qtyTextView.setText(String.valueOf(qty));
        incQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(qtyTextView.getText().toString());
                if(temp<=9){
                    temp++;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Maximum Limit is 10",Toast.LENGTH_SHORT).show();
                }

                qtyTextView.setText(String.valueOf(temp));
            }
        });
        decQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.parseInt(qtyTextView.getText().toString());
                if(temp>1){
                    temp--;
                }
                else{
                    Toast.makeText(getApplicationContext(),"Minimum Limit is 1",Toast.LENGTH_SHORT).show();
                }                qtyTextView.setText(String.valueOf(temp));
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
                Random rand = new Random();
                int pickedNumber = rand.nextInt((9999 - 1000) + 1) + 1000;
                CartItem cartItem = new CartItem(itemName, price, String.valueOf(qty), pickedNumber);
                myRef.child( "cart").push().setValue(cartItem);
                startActivity(new Intent(PurchaseActivity.this, ThankYouActivity.class).putExtra("pickITCode", pickedNumber));
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
