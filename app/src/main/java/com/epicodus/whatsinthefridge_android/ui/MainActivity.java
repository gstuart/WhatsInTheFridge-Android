package com.epicodus.whatsinthefridge_android.ui;

import android.graphics.Typeface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.registerLink) Button mRegisterLink;
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

        mLoginButton.setOnClickListener(this);
        mRegisterLink.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if (v == mRegisterLink) {
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);
        }
    }
}