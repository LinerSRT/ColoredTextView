package com.liner.coloredtextview;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColoredTextView coloredTextView = findViewById(R.id.coloredText);
        coloredTextView.setText(new ColoredTextView.Builder()
                .add("Im normal color\n")
                .add("Im red color\n", Color.RED)
                .add("Im magenta color\n", Color.MAGENTA)
                .add("Im green color", "#00ff00")
                .build());
    }
}