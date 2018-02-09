package com.pickit.kronos.pickit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

public class ThankYouActivity extends AppCompatActivity {

    private TextView mPickItCode;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_thank_you);

            //Initializing views
            mPickItCode = findViewById(R.id.pickItId);

            //Generating random number
            Random rand = new Random();
            int pickedNumber = rand.nextInt((9999 - 1000) + 1) + 1000;

            //Setting values to views
            mPickItCode.setText(String.valueOf(pickedNumber));
        }
}
