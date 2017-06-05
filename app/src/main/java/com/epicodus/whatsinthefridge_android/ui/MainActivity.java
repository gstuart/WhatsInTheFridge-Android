package com.epicodus.whatsinthefridge_android.ui;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.registerLink) TextView mRegisterLink;

    @Bind(R.id.emailAddressText) EditText mEmailAddressText;
    @Bind(R.id.passwordText) EditText mPasswordText;

    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mLoginButton.setTypeface(text);
        mRegisterLink.setTypeface(text);
        mAboutButton.setTypeface(text);
        mEmailAddressText.setTypeface(text);
        mPasswordText.setTypeface(text);

        mLoginButton.setOnClickListener(this);
        mRegisterLink.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mLoginButton) {
            String email = mEmailAddressText.getText().toString();
            String password = mPasswordText.getText().toString();

            if (email.length() == 0) {
                mEmailAddressText.setError("Valid email address is required");
            } else if (password.length() == 0) {
                mPasswordText.setError("Password is required");
            } else {
                Intent intent = new Intent(MainActivity.this, Fridge.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_LONG).show();
            }
        }
        if (v == mRegisterLink) {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
        if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreference(String ingredient1) {
        mEditor.putString(Constants.PREFERENCES_INGREDIENT_KEY, ingredient1).apply();
    }
}