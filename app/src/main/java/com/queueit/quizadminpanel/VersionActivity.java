package com.queueit.quizadminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VersionActivity extends AppCompatActivity {

    EditText etVersion;
    LinearLayout btnVersion;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("quiz");
        etVersion = findViewById(R.id.et_Version);
        btnVersion = findViewById(R.id.btn_Version);
        btnVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String version = etVersion.getText().toString();
                if (version.isEmpty()){
                    etVersion.setError("Fields is not null");
                    etVersion.requestFocus();
                    return;
                }else {

                    databaseReference.child("appVersion").setValue(version);
                    etVersion.setText("");
                }
            }
        });
    }
}