package com.example.volley2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        overview = findViewById(R.id.overview_tv);
        Intent intent = getIntent();

        overview.setText(intent.getExtras().getString("ov")); //put the description in text view
    }
}