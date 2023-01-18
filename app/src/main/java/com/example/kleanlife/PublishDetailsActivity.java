package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PublishDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView user, pID, location, rooms, bathRooms, floor, cost, date, invisibleName, clientName;
    Button back, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_details);

        Database dbh = new Database(this);

        imageView = findViewById(R.id.imageView7);
        user = findViewById(R.id.textView38);
        pID = findViewById(R.id.textView41);
        location = findViewById(R.id.textView42);
        rooms = findViewById(R.id.textView43);
        bathRooms = findViewById(R.id.textView44);
        floor = findViewById(R.id.textView45);
        cost = findViewById(R.id.textView46);
        date = findViewById(R.id.textView48);
        done = findViewById(R.id.button18);
        invisibleName = findViewById(R.id.textView50);
        clientName = findViewById(R.id.textView51);

        done.setBackgroundColor(Color.BLUE);

        String vName = getIntent().getStringExtra("name");
        invisibleName.setText(vName);
        String visible = invisibleName.getText().toString();

        ArrayList<Publish> arrayList = dbh.getPublish();
        Intent intent = getIntent();
        String pos = intent.getStringExtra("POSITION");
        int position = Integer.parseInt(pos);
        Publish publish = arrayList.get(position);

        byte[] image = publish.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        String uN = publish.getUserName();
        String propertyId = publish.getPropertyId();
        String loca = publish.getLocation();
        String room = publish.getRoomCount();
        String bath = publish.getBathroomCount();
        String flooringType = publish.getFlooringType();
        String pce = publish.getCost();
        String d = publish.getDate();

        imageView.setImageBitmap(bitmap);
        user.setText("Owner: "+uN);
        pID.setText("property Id: "+propertyId);
        location.setText("Location: "+loca);
        rooms.setText("Room(s): "+room);
        bathRooms.setText("Bathroom(s): "+bath);
        floor.setText("Floor Type: "+flooringType);
        cost.setText("Cost: "+pce);
        date.setText("Date: "+d);

        done.setOnClickListener(v -> {
            clientName.setText(uN);
            String name = clientName.getText().toString();
            int deletePublish = dbh.deletePublish(name);
            Toast.makeText(this, "Work Accepted and Done!", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this, ViewUserPublishActivity.class);
            intent1.putExtra("user", visible);
            startActivity(intent1);
        });


    }
}