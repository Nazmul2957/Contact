package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.contact.SharedPrefManager.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    EditText login_password_et,login_email_et;
    LinearLayout create_new_account;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_email_et = findViewById(R.id.login_email_et);
        login_password_et = findViewById(R.id.login_password_et);
        create_new_account = findViewById(R.id.create_new_account);
        login_btn = findViewById(R.id.login_btn);

        Log.d("login", "email "+SharedPrefManager.getInstance(MainActivity.this).getEmail());
        Log.d("login", "email "+SharedPrefManager.getInstance(MainActivity.this).getPassword());


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPrefManager.getInstance(MainActivity.this).getEmail() != null){
                    if (login_email_et.getText().toString().trim().equals(SharedPrefManager.getInstance(MainActivity.this).getEmail().toString()) && login_password_et.getText().toString().trim().equals(SharedPrefManager.getInstance(MainActivity.this).getPassword())){
                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please ensure your email and password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please registration first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            }
        });



    }
}