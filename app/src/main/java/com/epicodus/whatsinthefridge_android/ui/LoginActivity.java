package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordText) EditText mPasswordText;
    @Bind(R.id.signInButton) Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mEmailEditText.setTypeface(text);
        mPasswordText.setTypeface(text);
        mSignInButton.setTypeface(text);

        mSignInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mSignInButton) {
            String email = mEmailEditText.getText().toString();
            String password = mPasswordText.getText().toString();

            if (email.length() == 0) {
                mEmailEditText.setError("Valid email address is required");
            } else if (password.length() == 0) {
                mPasswordText.setError("Password is required");
            } else {
                Intent intent = new Intent(LoginActivity.this, Fridge.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

