package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KleanActivity extends AppCompatActivity {

    TextView name, userForm;
    Button propertyDetails, createPublish, viewPublish,
            houseownerReview, review, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klean);

        Database dbh = new Database(this);

        name = findViewById(R.id.textView8);
        userForm = findViewById(R.id.textView14);

        propertyDetails = findViewById(R.id.button6);
        createPublish = findViewById(R.id.button7);
        viewPublish = findViewById(R.id.button10);

        houseownerReview = findViewById(R.id.button8);
        review = findViewById(R.id.button11);
        back = findViewById(R.id.button9);


        String namE = getIntent().getStringExtra("user");
        name.setText(namE);

        String user = name.getText().toString();

        Cursor cursor = dbh.viewUserForm(user);
        cursor.moveToNext();
        userForm.setText(cursor.getString(4));

        String uTName = userForm.getText().toString();
        if (uTName.equals("HouseOwner")) {
            viewPublish.setVisibility(View.GONE);
            review.setVisibility(View.GONE);
        }else if (uTName.equals("Worker")) {
            propertyDetails.setVisibility(View.GONE);
            createPublish.setVisibility(View.GONE);
            houseownerReview.setVisibility(View.GONE);
        }

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        propertyDetails.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewPropertyDetailsActivity.class);
            intent.putExtra("name", user);
            startActivity(intent);
        });

        createPublish.setOnClickListener(v -> {
            Intent intent = new Intent(this, Published.class);
            intent.putExtra("name", user);
            startActivity(intent);
        });

        viewPublish.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewUserPublishActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        review.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        houseownerReview.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}