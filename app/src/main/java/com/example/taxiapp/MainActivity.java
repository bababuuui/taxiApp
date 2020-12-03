package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phoneET;
    private EditText firstNameET;
    private EditText surNameET;
    private Button submitBtn;

    private SharedPreferences sPref;

    private final String PHONE = "phoneSF";
    private final String FIRSTNAME ="firstNameSF";
    private final String SURNAME ="surNameSF";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("myTag",this + " is created");



        phoneET = findViewById(R.id.editTextTelNumber);
        firstNameET = findViewById(R.id.editTextName);
        surNameET = findViewById(R.id.editTextSurname);
        submitBtn = findViewById(R.id.submitBtn);

        loadUserInfo();

        View.OnClickListener registrationButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),SecondActivity.class);
                        intent.putExtra("Phone", phoneET.getText().toString());
                        intent.putExtra("Name", firstNameET.getText().toString());
                        intent.putExtra("SurName", surNameET.getText().toString());
                        startActivity(intent);
            }

        };

        findViewById(R.id.submitBtn).setOnClickListener(registrationButtonListener);



    }

    private void saveUserInfo(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(PHONE, phoneET.getText().toString());
        editor.putString(FIRSTNAME, firstNameET.getText().toString());
        editor.putString(SURNAME, surNameET.getText().toString());
        editor.commit();
        Toast.makeText(this, "User info has been saved :)", Toast.LENGTH_SHORT).show();


    }

    private void loadUserInfo(){
        sPref = getPreferences(MODE_PRIVATE);
        String phone = sPref.getString(PHONE,"");
        String firstName = sPref.getString(FIRSTNAME,"");
        String surName = sPref.getString(SURNAME,"");

        if(!phone.equals("") && !firstName.equals("") && !surName.equals("")){
            submitBtn.setText("LOGIN");
        }

        phoneET.setText(phone);
        surNameET.setText(firstName);
        firstNameET.setText( surName);
        Toast.makeText(this,"User info has been loaded :)", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        saveUserInfo();
        super.onDestroy();
    }
}