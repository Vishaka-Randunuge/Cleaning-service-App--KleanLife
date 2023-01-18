package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    TextView naME;
    Button giveRev, viewRev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        naME = findViewById(R.id.textView53);
        giveRev = findViewById(R.id.button20);
        viewRev = findViewById(R.id.button21);

        giveRev.setBackgroundColor(Color.BLUE);
        viewRev.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("user");
        naME.setText(name);
        String user = naME.getText().toString();

        giveRev.setOnClickListener(v -> {
            Intent intent = new Intent(this, GiveReviewActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        viewRev.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewReviewActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}