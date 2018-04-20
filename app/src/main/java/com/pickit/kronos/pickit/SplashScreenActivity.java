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

import com.pickit.kronos.pickit.Data.Constants;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SplashScreenActivity extends AppCompatActivity {

    private EditText pnrTextView;
    private Button mStartButton;
    private String pnrNumber;
    String tv="";

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


                    try {
                        SAXParserFactory factory = SAXParserFactory.newInstance();

                        SAXParser saxParser = factory.newSAXParser();


                        DefaultHandler handler = new DefaultHandler() {

                            boolean surname = false;
                            boolean given = false;
                            boolean title = false;
                            boolean arrival = false;
                            boolean departure = false;
                            boolean arrAirportCode = false;
                            boolean arrDate = false;
                            boolean arrTime = false;
                            boolean deptAirportCode = false;
                            boolean deptDate = false;
                            boolean deptTime = false;
                            boolean cinfo = false;


                            public void startElement(String uri, String localName, String qName,
                                                     Attributes attributes) throws SAXException {
                                if (qName.equalsIgnoreCase("ns2:surname")) {
                                    surname = true;
                                }
                                if (qName.equalsIgnoreCase("ns2:given")) {
                                    given = true;
                                }
                                if (qName.equalsIgnoreCase("ns2:title")) {
                                    title = true;
                                }
                                if (qName.equalsIgnoreCase("ns2:couponInfo")) {
                                    cinfo = true;
                                }
                                if (!cinfo) {
                                    if (qName.equalsIgnoreCase("ns2:Arrival")) {
                                        arrival = true;
                                        departure = false;
                                    } else if (!departure && qName.equalsIgnoreCase("ns2:AirportCode")) {
                                        arrAirportCode = true;
                                    } else if (!departure && qName.equalsIgnoreCase("ns2:Date")) {
                                        arrDate = true;

                                    } else if (!departure && qName.equalsIgnoreCase("ns2:Time")) {
                                        arrTime = true;

                                    }

                                    if (qName.equalsIgnoreCase("ns2:Departure")) {
                                        arrival = false;
                                        departure = true;

                                    } else if (!arrival && qName.equalsIgnoreCase("ns2:AirportCode")) {
                                        deptAirportCode = true;

                                    } else if (!arrival && qName.equalsIgnoreCase("ns2:Date")) {
                                        deptDate = true;

                                    } else if (!arrival && qName.equalsIgnoreCase("ns2:Time")) {
                                        deptTime = true;

                                    }
                                }

                            }//end of startElement method

                            public void endElement(String uri, String localName,
                                                   String qName) throws SAXException {
                                if (qName.equalsIgnoreCase("arrival")) {
                                    tv=(tv + "\n Arrival EndElement: " + qName);
                                }
                            }

                            public void characters(char ch[], int start, int length) throws SAXException {
                                if (surname) {
                                    String nameSurname = new String(ch, start, length);
                                    tv=tv + "\n\n Surname : " + nameSurname;
                                    surname = false;
                                }
                                if (given) {
                                    String nameGiven = new String(ch, start, length);
                                    tv=(tv + "\n Given : " + nameGiven);
                                    given = false;
                                }
                                if (title) {
                                    String nameTitle = new String(ch, start, length);
                                    tv=(tv + "\n Title : " + nameTitle);
                                    title = false;
                                }


                                if (arrAirportCode) {
                                    String aAirCode = new String(ch, start, length);
                                    tv=(tv + "\n Arrival Airport Code : " + aAirCode);
                                    arrAirportCode = false;
                                }
                                if (arrDate) {
                                    String aDate = new String(ch, start, length);
                                    tv=(tv + "\n Arrival Date : " + aDate);
                                    arrDate = false;
                                }
                                if (arrTime) {
                                    String aTime = new String(ch, start, length);
                                    tv=(tv + "\n Arrival Time: " + aTime);
                                    arrTime = false;
                                }
                                if (deptAirportCode) {
                                    String dAirCode = new String(ch, start, length);
                                    tv=(tv + "\n Departure Airport Code : " + dAirCode);
                                    deptAirportCode = false;
                                }
                                if (deptDate) {
                                    String dDate = new String(ch, start, length);
                                    tv=tv + "\n Departure Date: " + dDate;
                                    deptDate = false;
                                }
                                if (deptTime) {
                                    String dTime = new String(ch, start, length);
                                    tv=(tv + "\n Departure Time: " + dTime);
                                    deptTime = false;
                                }
// **/
                            }//end of characters
                            //method
                        };//end of DefaultHandler object

                        InputStream is = getAssets().open("OrderRetrieve.xml");
                        saxParser.parse(is, handler);
                        Toast.makeText(getApplicationContext(), tv, Toast.LENGTH_SHORT).show();


                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    String x =  "airportCode: FRA,\nairlineCode: XQ,\nflightNumber: 141,\ngate: 2";

                    Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT).show();


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
