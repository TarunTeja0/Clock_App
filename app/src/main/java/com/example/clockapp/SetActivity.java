package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class SetActivity extends AppCompatActivity {
    public static int hour;
    public static int min;
    private TimePicker timePicker;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        button = findViewById(R.id.saveButton);
        timePicker = findViewById(R.id.timePicker);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = timePicker.getHour();
                min = timePicker.getMinute();
                Log.d("min",String.valueOf(min));
                Log.d("hour",String.valueOf(hour));
                Intent intent = getIntent();
                intent.putExtra("hr", hour);
                intent.putExtra("min", min);
                setResult(100, intent);
                finish();
            }
        });

    }
}