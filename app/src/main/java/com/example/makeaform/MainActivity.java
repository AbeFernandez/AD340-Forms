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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name, email, userName, dateIn;
    private TextView errorMessage;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        userName = findViewById(R.id.username);
        dateIn = findViewById(R.id.date);
        MainActivity.this.errorMessage = findViewById(R.id.error_message);

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
                        month = month + 1;
                        String date = month + "/" + day + "/" + year;
                        dateIn.setText(date);

                        minAdultAge.add(Calendar.YEAR, -18);
                        if (minAdultAge.before(userAge)) {
                            MainActivity.this.errorMessage.setText("You are under 18");
                            MainActivity.this.errorMessage.setTextColor(getResources().getColor(R.color.red));
                        }else{

                            MainActivity.this.errorMessage.setText("Good!");

                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    public void goToSecondActivity(View view) {
        String name_field = name.getText().toString();
        String email_field = email.getText().toString();
        String user_name = userName.getText().toString();
        String user_date = MainActivity.this.errorMessage.getText().toString();

        if (user_date != "You are under 18" && user_date != "" && name_field != "" && email_field != "" && user_name != ""){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);

            intent.putExtra("keyname", user_name);
            startActivity(intent);
        }
    }
}