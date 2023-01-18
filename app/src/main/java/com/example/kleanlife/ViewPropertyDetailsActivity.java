package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPropertyDetailsActivity extends AppCompatActivity {

    TextView user, roomCount, noOfBathrooms, flooringType, location, propertyId;
    Button backBtn, createOrUpdate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_details);

        Database dbh = new Database(this);

        user = findViewById(R.id.textView19);
        backBtn = findViewById(R.id.button16);
        createOrUpdate = findViewById(R.id.button17);
        roomCount = findViewById(R.id.textView29);
        noOfBathrooms = findViewById(R.id.textView30);
        flooringType = findViewById(R.id.textView31);
        location = findViewById(R.id.textView32);
        propertyId = findViewById(R.id.textView33);
        imageView = findViewById(R.id.imageView6);

        backBtn.setBackgroundColor(Color.BLUE);
        createOrUpdate.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("name");
        user.setText(name);

        String uN = user.getText().toString();

        Cursor cursor = dbh.viewProperty(uN);
        if (cursor.moveToNext()) {
            roomCount.setText(cursor.getString(2));
            noOfBathrooms.setText(cursor.getString(3));
            flooringType.setText(cursor.getString(4));
            location.setText(cursor.getString(5));
            propertyId.setText(cursor.getString(0));
            byte[] image = cursor.getBlob(6);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);
        } else {
            Toast.makeText(this, "Cannot find Property details. So please enter property details", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PropertyDetailsActivity.class);
            intent.putExtra("name", uN);
            startActivity(intent);
        }


        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, KleanActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });

        createOrUpdate.setOnClickListener(v -> {
            Intent intent= new Intent(this, PropertyDetailsActivity.class);
            intent.putExtra("name", uN);
            startActivity(intent);
        });
    }
}