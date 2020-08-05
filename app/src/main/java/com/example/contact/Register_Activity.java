package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contact.SharedPrefManager.SharedPrefManager;

public class Register_Activity extends AppCompatActivity {

    EditText reg_full_name_et, reg_email_et, reg_phone_number_et, reg_password_et, reg_confirm_password_et;
    Button reg_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        reg_full_name_et = findViewById(R.id.reg_full_name_et);
        reg_email_et = findViewById(R.id.reg_email_et);
        reg_phone_number_et = findViewById(R.id.reg_phone_number_et);
        reg_password_et = findViewById(R.id.reg_password_et);
        reg_confirm_password_et = findViewById(R.id.reg_confirm_password_et);

        reg_btn = findViewById(R.id.reg_btn);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(reg_full_name_et.getText())) {
                    reg_full_name_et.setError("Please give your name!");
                } else if (TextUtils.isEmpty(reg_email_et.getText())) {
                    reg_email_et.setError("Please give your email!");
                } else if (TextUtils.isEmpty(reg_phone_number_et.getText())) {
                    reg_phone_number_et.setError("Please give your mobile!");
                } else if (TextUtils.isEmpty(reg_password_et.getText())) {
                    reg_password_et.setError("Please give your password!");
                } else if (!reg_password_et.getText().toString().trim().equals(reg_confirm_password_et.getText().toString().trim())) {
                    Toast.makeText(Register_Activity.this, "Password are not same", Toast.LENGTH_SHORT).show();
                } else {
                    setUserDetails();
                }
            }
        });

    }

    private void setUserDetails() {
        SharedPrefManager.getInstance(Register_Activity.this).saveEmail(reg_email_et.getText().toString().trim());
        SharedPrefManager.getInstance(Register_Activity.this).saveUserName(reg_full_name_et.getText().toString().trim());
        SharedPrefManager.getInstance(Register_Activity.this).savePhoneNumber(reg_phone_number_et.getText().toString().trim());
        SharedPrefManager.getInstance(Register_Activity.this).savePassword(reg_password_et.getText().toString().trim());

        Toast.makeText(Register_Activity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

        Log.d("login", "email " + SharedPrefManager.getInstance(Register_Activity.this).getEmail());
        Log.d("login", "email " + SharedPrefManager.getInstance(Register_Activity.this).getPassword());

        Intent intent = new Intent(Register_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}