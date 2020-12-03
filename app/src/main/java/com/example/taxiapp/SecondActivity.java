package com.example.taxiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView nameTextView;
    TextView phoneTextView;
    TextView routeTextView;
    Button callTaxBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameTextView = findViewById(R.id.nameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        routeTextView = findViewById(R.id.routeTV);
        callTaxBtn = findViewById(R.id.callTaxiBtn);
        //load data from the main activity

        Intent intent = getIntent();
        nameTextView.setText(String.format("%s %s", intent.getStringExtra("Name"), intent.getStringExtra("SurName")));
        phoneTextView.setText(intent.getStringExtra("Phone"));




        View.OnClickListener setPathBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),ThirdActivity.class);
                startActivityForResult(intent,1);
            }

        };

        View.OnClickListener callTaxiBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Wait for taxi. Good luck1!", Toast.LENGTH_SHORT).show();
            }

        };

        findViewById(R.id.setPathBtn).setOnClickListener(setPathBtnListener);
        findViewById(R.id.callTaxiBtn).setOnClickListener(callTaxiBtnListener);


    }

    @Override
    protected void onRestart() {
        Log.d("my",this + " is restarted");
        super.onRestart();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (data == null) {
            return;
        }
        String streetFrom = data.getStringExtra("streetFrom");
        String houseFrom = data.getStringExtra("houseFrom");
        String flatFrom = data.getStringExtra("flatFrom");

        String streetTo = data.getStringExtra("streetTo");
        String houseTo = data.getStringExtra("houseTo");
        String flatTo = data.getStringExtra("flatTo");



        // if the user provided input on third activity
        if(!streetFrom.equals("") || !streetTo.equals("") ||  !houseFrom.equals("") || !houseTo.equals("") ){
            callTaxBtn.setEnabled(true);
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append("Taxi will arrive at " + streetFrom + " " + houseFrom + " " + flatFrom + " in 5 minutes and take you in" +
                    streetTo + " " + houseTo + " " + flatTo + " , if you are agree click Call Taxi");
            routeTextView.setText(stringBuilder);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}