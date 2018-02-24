package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    private EditText pnrTextView;
    private Button mStartButton;
    private String pnrNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide top bars
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Autocomplete airport selection
        pnrTextView = findViewById(R.id.splash_screen_airport_code);
        mStartButton = findViewById(R.id.splash_screen_start);
        //what happens on continue button click
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnrNumber = pnrTextView.getText().toString();

                //TODO:Hardcoded PNR check
                if (pnrNumber.equals("ABEM4Z")) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("PNR", pnrNumber);
                    editor.putString("FirstName", "ADAMS");
                    editor.putString("LastName", "ANTON");
                    editor.putString("Phone", "313712387");
                    editor.putString("Email", "antonadams@hetnet.nl");
                    editor.putString("Address", "Nieuwe Plaatsen 201a NACHGERAAD 7501KO");
                    editor.putString("DepartureDate", "2018-03-15");
                    editor.putString("DepartureTime", "15:55");
                    editor.putString("DepartureAirport", "COK/1");   //TODO:Make COK/1 as FRA
                    editor.putString("ArrivalDate", "2018-03-15");
                    editor.putString("ArrivalTime", "21:20");
                    editor.putString("ArrivalAirport", "AYT");
                    editor.putString("AirlineID", "XQ");
                    editor.putString("FlightNumber", "141");
                    editor.apply();
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Enter a valid PNR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Enable Send button when there's text to send
        pnrTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mStartButton.setEnabled(true);
                } else {
                    mStartButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
