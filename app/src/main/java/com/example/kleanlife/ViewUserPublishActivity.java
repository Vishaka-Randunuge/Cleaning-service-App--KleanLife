package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewUserPublishActivity extends AppCompatActivity {

    ListView listView;
    TextView name;
    Button backBtn;
    ArrayList<Publish> arrayList;
    PublishAdapt adapter;
    Database dbh = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_publish);


        backBtn = findViewById(R.id.button19);
        listView = findViewById(R.id.listView);
        name = findViewById(R.id.textView49);



        String namE= getIntent().getStringExtra("user");
        name.setText(namE);
        String user = name.getText().toString();

        showPublishData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewUserPublishActivity.this, PublishDetailsActivity.class);
                intent.putExtra("POSITION", String.valueOf(position));
                intent.putExtra("name", user);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, KleanActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }

    private void showPublishData() {
        arrayList = dbh.getPublish();
        adapter = new PublishAdapt(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}