package com.example.school_speedrun_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button switchActivityBtn, savePrefsBtn;
    private EditText nameEdit, ageEdit;
    private TextView nameText, ageText;

    public static final String USER_PREFERENCES= "user";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";

    SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);


        userPreferences = getSharedPreferences(USER_PREFERENCES, this.MODE_PRIVATE);


        if(userPreferences.contains(APP_PREFERENCES_AGE)){
            ageText.setText(userPreferences.getString(APP_PREFERENCES_AGE, ""));
        }

        if(userPreferences.contains(APP_PREFERENCES_NAME)){
            nameText.setText(userPreferences.getString(APP_PREFERENCES_NAME, ""));
        }



        savePrefsBtn = findViewById(R.id.savePrefBtn);
        savePrefsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit = findViewById(R.id.nameEdit);
                ageEdit = findViewById(R.id.ageEdit);


                nameText.setText(nameEdit.getText().toString());
                ageText.setText(ageEdit.getText().toString());
                SharedPreferences.Editor prefsEditor = userPreferences.edit();
                prefsEditor.putString(APP_PREFERENCES_AGE, ageText.getText().toString());
                prefsEditor.putString(APP_PREFERENCES_NAME, nameText.getText().toString());
                prefsEditor.apply();
            }
        });


        switchActivityBtn = findViewById(R.id.switchActivityBtn);
        switchActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(a);
            }
        });
    }



}