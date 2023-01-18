package com.example.kleanlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView register, forgotPass;
    Button login;
    EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database dbh = new Database(this);

        register = findViewById(R.id.textView4);
        name = findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPassword3);
        login = findViewById(R.id.button3);
        forgotPass = findViewById(R.id.textView3);

        login.setBackgroundColor(Color.BLUE);

        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String user = name.getText().toString();
            String passw = pass.getText().toString();

            if (user.equals("") || pass.equals("")) {
                Toast.makeText(LoginActivity.this, "You are missing information in fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkUserPass = dbh.checkNamePass(user, passw);

                if (checkUserPass == true) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), KleanActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid information. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPass.setOnClickListener(v -> {
            String user = name.getText().toString();
            Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
            startActivity(intent);
        });
    }
}