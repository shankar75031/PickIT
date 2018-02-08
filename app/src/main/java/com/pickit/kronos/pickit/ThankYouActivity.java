package com.pickit.kronos.pickit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ThankYouActivity extends AppCompatActivity {

    private TextView pickItCode;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.thank_you);

            pickItCode = (TextView) findViewById(R.id.pickItId);
            Random rand = new Random();
            int pickedNumber = rand.nextInt((9999 - 1000) + 1) + 1000;
            pickItCode.setText(String.valueOf(pickedNumber));
        }
}
