package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class GiveReviewActivity extends AppCompatActivity {

    TextView naME, date,
             receiver, reView;
    Button back, add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_review);

        Database dbh = new Database(this);

        naME = findViewById(R.id.textView55);
        date = findViewById(R.id.textView56);
        receiver = findViewById(R.id.editTextTextPersonName7);
        reView = findViewById(R.id.editTextTextMultiLine);
        back = findViewById(R.id.button22);
        add = findViewById(R.id.button23);


        long d = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy");
        String dateString = sdf.format(d);
        date.setText(dateString);

        back.setBackgroundColor(Color.BLUE);
        add.setBackgroundColor(Color.BLUE);

        String user = getIntent().getStringExtra("user");
        naME.setText(user);
        String name = naME.getText().toString();

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, KleanActivity.class);
            intent.putExtra("user", name);
            startActivity(intent);
        });

        add.setOnClickListener(v -> {
            String fR = receiver.getText().toString();
            String rev = reView.getText().toString();
            String dateText = date.getText().toString();

            if (fR.equals("") || rev.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean insert = dbh.insertReview(name, rev, fR, dateText);
                if (insert == true) {
                    Toast.makeText(this, "Review added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}