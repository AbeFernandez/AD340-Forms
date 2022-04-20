package com.example.makeaform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userName, dateIn;
    private TextView errorMessege;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.username);
        dateIn = findViewById(R.id.date);
        errorMessege = findViewById(R.id.error_messege);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar userAge = new GregorianCalendar(year,month,day);
                        Calendar minAdultAge = new GregorianCalendar();
                        minAdultAge.add(Calendar.YEAR, -18);
                        if (minAdultAge.before(userAge)) {
                            errorMessege.setText("You are under 18");
                            errorMessege.setTextColor(getResources().getColor(R.color.red));
                        }else{
                            month = month + 1;
                            String date = month + "/" + day + "/" + year;
                            dateIn.setText(date);
                            errorMessege.setText("Good!");
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }


    public void goToSecondActivity(View view) {
        String user_name = userName.getText().toString();
        String user_date = errorMessege.getText().toString();
        if (user_date != "You are under 18"){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);

            intent.putExtra("keyname", user_name);
            startActivity(intent);
        }

    }
}