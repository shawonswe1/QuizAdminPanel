package com.queueit.quizadminpanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView cvSlide,cvHeading,cvCategory,cvVersion;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvSlide = findViewById(R.id.cv_Slide);
        cvHeading = findViewById(R.id.cv_Heading);
        cvCategory = findViewById(R.id.cv_Category);
        cvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,CategoryActivity.class));
//                finish();
            }
        });
        cvVersion = findViewById(R.id.cv_Version);
        cvVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,VersionActivity.class));
            }
        });
    }
}