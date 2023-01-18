package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewReviewActivity extends AppCompatActivity {

    TextView name;
    Button back;
    ListView listView;
    ArrayList<Review> arrayList;
    ReviewAdapt adapter;
    Database dbh = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        back = findViewById(R.id.button24);
        name = findViewById(R.id.textView57);
        listView = findViewById(R.id.list);

        back.setBackgroundColor(Color.BLUE);

        String namE = getIntent().getStringExtra("user");
        name.setText(namE);


        showRevData();

        back.setOnClickListener(v -> {
            String uN = name.getText().toString();
            Intent intent = new Intent(this, KleanActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });
    }

    private void showRevData() {
        String user = name.getText().toString();
        arrayList = dbh.getrev(user);
        adapter = new ReviewAdapt(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}