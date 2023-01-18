package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button backBtn, registerBtn;
    EditText name, pass, confirmPass, response;
    Spinner spinner1;
    TextView textViewValidity, userForm;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Database dbh = new Database(this);

        spinner1 = findViewById(R.id.spinner);
        textViewValidity = findViewById(R.id.textView6);
        userForm = findViewById(R.id.textView9);
        radioGroup = findViewById(R.id.radioGroup);

        name = findViewById(R.id.editTextTextPersonName);
        pass = findViewById(R.id.editTextTextPassword);
        confirmPass = findViewById(R.id.editTextTextPassword2);
        response = findViewById(R.id.editTextTextPersonName2);

        backBtn = findViewById(R.id.button2);
        registerBtn = findViewById(R.id.button);


        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(this);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        registerBtn.setOnClickListener(v -> {
            String uName = name.getText().toString();
            String passw = pass.getText().toString();
            String conPass = confirmPass.getText().toString();
            String ans = response.getText().toString();
            String utype = userForm.getText().toString();
            String secQuestion = textViewValidity.getText().toString();

            if (uName.equals("") || pass.equals("") || conPass.equals("") || ans.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (passw.equals(conPass)) {
                    Boolean checkUser = dbh.checkName(uName);
                    if (checkUser == false) {
                        Boolean insert = dbh.insertUser(uName, passw, secQuestion, ans, utype);
                        if (insert == true) {
                            Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        textViewValidity.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        userForm.setText(radioButton.getText());
    }
}