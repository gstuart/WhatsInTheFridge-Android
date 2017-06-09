package com.epicodus.whatsinthefridge_android.ui;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.whatsinthefridge_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity  implements View.OnClickListener {
    @Bind(R.id.firstNameText) EditText mFirstName;
    @Bind(R.id.lastNameText) EditText mLastName;
    @Bind(R.id.emailText) EditText mEmail;
    @Bind(R.id.passwordText) EditText mPassword;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPassword;

    @Bind(R.id.registerButton) Button mRegistrationButton;

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
            mFirstName.setTypeface(text);
            mLastName.setTypeface(text);
            mEmail.setTypeface(text);
            mPassword.setTypeface(text);
            mConfirmPassword.setTypeface(text);
            mRegistrationButton.setTypeface(text);

        mRegistrationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        createNewUser();
    }

    private void createNewUser() {
        final String firstName = mFirstName.getText().toString().trim();
        final String lastName = mLastName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener< AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed. Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

//        mRegistrationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if(firstName.length() == 0) {
//                    mFirstName.setError("First name is required");
//                } else if(lastName.length() == 0) {
//                    mLastName.setError("Last name is required");
//                } else if(email.length() == 0) {
//                    mEmail.setError("Valid email address is required");
//                } else if(password.length() == 0) {
//                    mPassword.setError("Password is required");
//                } else {
//                    Intent intent = new Intent(CreateAccountActivity.this, Fridge.class);
//                    intent.putExtra("firstName", firstName);
//                    intent.putExtra("lastName", lastName);
//                    intent.putExtra("email", email);
//                    intent.putExtra("password", password);
//
//                    Toast.makeText(CreateAccountActivity.this, "Welcome To What's In The Fridge", Toast.LENGTH_LONG).show();
//                    startActivity(intent);
//                }
//            }
//        });

}