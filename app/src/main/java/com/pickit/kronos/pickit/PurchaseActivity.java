package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pickit.kronos.pickit.Data.Constants;
import com.pickit.kronos.pickit.Objects.CartItem;

public class PurchaseActivity extends AppCompatActivity {

    private TextView itemNameTextView;
    private TextView priceTextView;
    private TextView descTextView;
    private TextView qtyTextView;
    private Button incQty;
    private Button decQty;
    private Button addToCart;

    private String itemName;
    private String price;
    private String desc;
    private int qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Bundle extras = getIntent().getExtras();
        itemName = extras.getString("itemName");
        price = extras.getString("Price");
        desc = extras.getString("Description");
        qty = extras.getInt("qty");
        FirebaseApp.initializeApp(this);

        initViews();

        itemNameTextView.setText(itemName);
        priceTextView.setText(price);
        descTextView.setText(desc);
        qtyTextView.setText(String.valueOf(qty));

        setOnClickListners();
    }

    private void setOnClickListners() {
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

                String airportCode = pref.getString("airport_terminal", "").substring(0, 3);
                String terminalNumber = pref.getString("airport_terminal", "").substring(3);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/" + airportCode + "/" + terminalNumber + "/carts");
                CartItem cartItem = new CartItem(itemName, price, String.valueOf(qty));
                myRef.child("cart").push().setValue(cartItem);
                startActivity(new Intent(PurchaseActivity.this, ThankYouActivity.class));
            }
        });
    }

    private void initViews() {
        itemNameTextView = findViewById(R.id.itemname_display);
        priceTextView = findViewById(R.id.dynam_price);
        descTextView = findViewById(R.id.dynam_desc);
        qtyTextView = findViewById(R.id.dynam_quantity);
        incQty = findViewById(R.id.increase);
        decQty = findViewById(R.id.decrease);
        addToCart = findViewById(R.id.button3);
    }
}
