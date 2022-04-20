package com.example.makeaform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView userName, greetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        userName = findViewById(R.id.textView);
        greetings = findViewById(R.id.greetings);

        String userNameValue = getIntent().getStringExtra("keyname");
        greetings.append(" ");
        greetings.append(userNameValue);
        greetings.append("!");

    }
}