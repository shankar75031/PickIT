package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Bundle extras = getIntent().getExtras();
        final String itemName = extras.getString("itemName");
        final String price = extras.getString("Price");
        String desc = extras.getString("Description");
        final int qty = extras.getInt("qty");
        FirebaseApp.initializeApp(this);

        itemNameTextView = (TextView)findViewById(R.id.itemname_display);
        priceTextView =(TextView)findViewById(R.id.dynam_price);
        descTextView = (TextView)findViewById(R.id.dynam_desc);
        qtyTextView = (TextView)findViewById(R.id.dynam_quantity);
        incQty = (Button)findViewById(R.id.increase);
        decQty = (Button)findViewById(R.id.decrease);
        addToCart = (Button)findViewById(R.id.button3);

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

                String airportCode = pref.getString("airport_terminal", "").substring(0,3);
                String terminalNumber = pref.getString("airport_terminal", "").substring(3);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/" + airportCode + "/" + terminalNumber+"/carts");
                CartItem cartItem = new CartItem(itemName,price,String.valueOf(qty));
                myRef.child( "cart").push().setValue(cartItem);
                startActivity(new Intent(PurchaseActivity.this, ThankYouActivity.class));
            }
        });
    }
}
