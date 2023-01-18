package com.example.kleanlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

public class Published extends AppCompatActivity {

    TextView name, roomCount, noOfBathrooms, flooringType, location, propertyId, room, bathRoom, floor, cost, date;
    Button backBtn, createBtn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_published);

        Database dbh = new Database(this);

        name = findViewById(R.id.textView25);
        backBtn = findViewById(R.id.button16);
        roomCount = findViewById(R.id.textView29);
        noOfBathrooms = findViewById(R.id.textView31);
        flooringType = findViewById(R.id.textView33);
        location = findViewById(R.id.textView26);
        propertyId = findViewById(R.id.textView28);
        imageView = findViewById(R.id.imageView6);
        room = findViewById(R.id.textView30);
        bathRoom = findViewById(R.id.textView32);
        floor = findViewById(R.id.textView34);
        cost = findViewById(R.id.textView36);
        date = findViewById(R.id.textView39);
        createBtn = findViewById(R.id.button15);

        long d = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy");
        String dateString = sdf.format(d);
        date.setText(dateString);

        String user = getIntent().getStringExtra("name");
        name.setText(user);
        room.setText("Room(s)");
        bathRoom.setText("Bathroom(s)");
        floor.setText("Floor type");
        String uN = name.getText().toString();

        Cursor cursor = dbh.viewProperty(uN);
        if (cursor.moveToNext()){
            roomCount.setText(cursor.getString(2));
            noOfBathrooms.setText(cursor.getString(3));
            flooringType.setText(cursor.getString(4));
            location.setText(cursor.getString(5));
            propertyId.setText(cursor.getString(0));
            byte[] image = cursor.getBlob(6);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bmp);

            String floorTy = flooringType.getText().toString();
            int countRoom = Integer.parseInt(roomCount.getText().toString());
            int countBath = Integer.parseInt(noOfBathrooms.getText().toString());
            switch (floorTy) {
                case "Wooden Floor": {
                    int costRoom = countRoom * 1500;
                    int costBath = countBath * 1300;
                    int costAll = costRoom + costBath;
                    cost.setText(String.valueOf(costAll));
                    break;
                }
                case "Concrete Floor": {
                    int costRoom = countRoom * 650;
                    int costBath = countBath * 220;
                    int costAll = costRoom + costBath;
                    cost.setText(String.valueOf(costAll));
                    break;
                }
                case "Tile": {
                    int costRoom = countRoom * 1400;
                    int costBath = countBath * 1200;
                    int costAll = costRoom + costBath;
                    cost.setText(String.valueOf(costAll));
                    break;
                }
                case "Marble Floor": {
                    int costRoom = countRoom * 1300;
                    int costBath = countBath * 1100;
                    int costAll = costRoom + costBath;
                    cost.setText(String.valueOf(costAll));
                    break;
                }
            }
        }  else {
            Toast.makeText(this, "Please enter property details", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PropertyDetailsActivity.class);
            intent.putExtra("name", uN);
            startActivity(intent);
        }

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, KleanActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });


            createBtn.setOnClickListener(v -> {

                String namE = name.getText().toString();
                String pID = propertyId.getText().toString();
                String rooms = roomCount.getText().toString();
                String bathRooms = noOfBathrooms.getText().toString();
                String floor = flooringType.getText().toString();
                String loc = location.getText().toString();
                byte[] img = imageViewToByte(imageView);
                String pR = cost.getText().toString();
                String dateText = date.getText().toString();

                int deletePublish = dbh.deletePublish(namE);
                Boolean insert = dbh.insertPublish(namE, pID, rooms, bathRooms, floor, loc, img, pR, dateText);
                if (insert == true) {
                    Toast.makeText(this, "Successful Publish!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private byte[] imageViewToByte(@NonNull ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}