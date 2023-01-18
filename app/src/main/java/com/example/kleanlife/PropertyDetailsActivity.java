package com.example.kleanlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PropertyDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView uN, flooringType;
    EditText location, roomCount, noOfBathrooms, propertyId;
    Spinner spinner;
    Button back, browse, add;
    ImageView imageView;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        Database dbh = new Database(this);

        uN = findViewById(R.id.textView17);
        flooringType = findViewById(R.id.textView23);
        spinner = findViewById(R.id.spinner3);
        back = findViewById(R.id.button13);
        browse = findViewById(R.id.button12);
        imageView = findViewById(R.id.imageView3);
        add = findViewById(R.id.button14);
        location = findViewById(R.id.editTextTextPersonName6);
        roomCount = findViewById(R.id.editTextNumber);
        noOfBathrooms = findViewById(R.id.editTextNumber2);
        propertyId = findViewById(R.id.editTextNumber4);



        String name = getIntent().getStringExtra("name");
        uN.setText(name);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.floors, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        back.setOnClickListener(v -> {
            String uName = uN.getText().toString();
            Intent intent = new Intent(this, ViewPropertyDetailsActivity.class);
            intent.putExtra("name", uName);
            startActivity(intent);
        });


        browse.setOnClickListener(v -> {
            openGallery();
        });

        add.setOnClickListener(v -> {
            String id = propertyId.getText().toString();
            String user = uN.getText().toString();
            String rooms = roomCount.getText().toString();
            String bathRooms = noOfBathrooms.getText().toString();
            String floor = flooringType.getText().toString();
            String loc = location.getText().toString();
            byte[] img = imageViewToByte(imageView);

            int deleteProperty = dbh.deleteProperty(user);

            if (id.equals("") || user.equals("") || loc.equals("") || rooms.equals("")) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkProperty = dbh.checkProperty(user);
                if (checkProperty == false) {
                    Boolean insert = dbh.insertPropertyDetails(id, user, rooms, bathRooms, floor, loc, img);
                    if (insert == true) {
                        Toast.makeText(this, "Successful property registration", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(this, ViewPropertyDetailsActivity.class);
                        intent.putExtra("name", user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Property already exists!", Toast.LENGTH_SHORT).show();
                }
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

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        flooringType.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}