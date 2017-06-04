package com.epicodus.whatsinthefridge_android;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {
    @Bind(R.id.firstNameText) EditText mFirstName;
    @Bind(R.id.lastNameText) EditText mLastName;
    @Bind(R.id.emailText) EditText mEmail;
    @Bind(R.id.passwordText) EditText mPassword;

    @Bind(R.id.registerButton) Button mRegistrationButton;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mFirstName.setTypeface(text);
        mLastName.setTypeface(text);
        mEmail.setTypeface(text);
        mPassword.setTypeface(text);
        mRegistrationButton.setTypeface(text);

        mRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(firstName.length() == 0) {
                    mFirstName.setError("First name is required");
                } else if(lastName.length() == 0) {
                    mLastName.setError("Last name is required");
                } else if(email.length() == 0) {
                    mEmail.setError("Valid email address is required");
                } else if(password.length() == 0) {
                    mPassword.setError("Password is required");
                } else {
                    Intent intent = new Intent(RegistrationActivity.this, Fridge.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);

                    Toast.makeText(RegistrationActivity.this, "Welcome To What's In The Fridge", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}