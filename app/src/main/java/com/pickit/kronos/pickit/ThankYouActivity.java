package com.pickit.kronos.pickit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
            setContentView(R.layout.activity_thank_you);
            Bundle extras = getIntent().getExtras();
            int pickedNumber = extras.getInt("pickITCode");

            pickItCode = findViewById(R.id.pickItId);

            pickItCode.setText(String.valueOf(pickedNumber));
        }
}
