package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {


    EditText streetToET;
    EditText houseToET;
    EditText flatToET;


    EditText streetFromET;
    EditText houseFromET;
    EditText flatFromET;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        streetFromET = findViewById(R.id.streetFromET);
        streetToET = findViewById(R.id.streetToET);
        houseFromET = findViewById(R.id.houseFromET);
        houseToET = findViewById(R.id.houseToET);
        flatFromET = findViewById(R.id.flatFromET);
        flatToET = findViewById(R.id.flatToET);


        View.OnClickListener okBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("streetFrom",streetFromET.getText().toString());
                intent.putExtra("houseFrom",houseFromET.getText().toString());
                intent.putExtra("flatFrom",flatFromET.getText().toString());
                intent.putExtra("streetTo",streetToET.getText().toString());
                intent.putExtra("houseTo",houseToET.getText().toString());
                intent.putExtra("flatTo",flatToET.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }

        };


        findViewById(R.id.okBtn).setOnClickListener(okBtnListener);

    }








}