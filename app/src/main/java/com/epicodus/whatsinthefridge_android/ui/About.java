package com.epicodus.whatsinthefridge_android.ui;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class About extends AppCompatActivity {
    @Bind(R.id.aboutTitle) TextView mAboutTitle;
    @Bind(R.id.aboutDescription) TextView mAboutDescription;
    @Bind(R.id.contact_title) TextView mContactTitle;
    @Bind(R.id.contact_name)TextView mContactName;
    @Bind(R.id.code) TextView mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Typeface boldText = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams_bold.ttf");
        mAboutTitle.setTypeface(boldText);
        mContactTitle.setTypeface(boldText);

        Typeface Text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mAboutDescription.setTypeface(Text);
        mContactName.setTypeface(Text);
        mCode.setTypeface(Text);

    }
}
