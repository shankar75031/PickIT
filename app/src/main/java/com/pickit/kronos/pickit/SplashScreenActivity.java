package com.pickit.kronos.pickit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.pickit.kronos.pickit.Data.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private AutoCompleteTextView mAirportSelectTextView;
    private Button mStartButton;
    private Spinner mTerminalSelectSpinner;
    private String airportCode;
    private String terminal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to hide top bars
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        initViews();

        //Autocomplete airport selection
        String[] countries = getResources().getStringArray(R.array.airport_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,countries);
        mAirportSelectTextView.setAdapter(adapter);

        //to select terminal
        mAirportSelectTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                //String selection = (String) parent.getItemAtPosition(position);

                //to set list of terminals in spinner
                airportCode = (String) parent.getItemAtPosition(position);
                airportCode = airportCode.substring(0,3);
                int id = getResources().getIdentifier(airportCode, "array", getPackageName());
                List<String> terminalList = Arrays.asList(getResources().getStringArray(id));
                ArrayList<String> terminalArrayList = new ArrayList<>();
                terminalArrayList.addAll(terminalList);
                ArrayAdapter<String> adapter = new ArrayAdapter<>
                        (getApplicationContext(),android.R.layout.simple_list_item_1,terminalArrayList);
                mTerminalSelectSpinner.setAdapter(adapter);

            }
        });


        //what happens on continue button click
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terminal = Integer.toString(mTerminalSelectSpinner.getSelectedItemPosition() + 1);

                SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_FILE, 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("airport_terminal", airportCode+terminal);
                editor.apply();
                if (!terminal.equals("null"))
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                else
                    Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Initializing views
    private void initViews() {
        mAirportSelectTextView = findViewById(R.id.splash_screen_airport_code);
        mStartButton = findViewById(R.id.splash_screen_start);
        mTerminalSelectSpinner = findViewById(R.id.splash_screen_terminal_select_spinner);
    }
}
