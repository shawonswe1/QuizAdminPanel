package com.queueit.quizadminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.queueit.quizadminpanel.ModelClass.QuestionModel;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    //    private StorageReference mStorageRef;
    private ValueEventListener valueEventListener;
    EditText question,opA,opB,opC,opD,answer;
    LinearLayout btnSubmit;
    String categoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.et_Question);
        opA = findViewById(R.id.et_OptionA);
        opB = findViewById(R.id.et_OptionB);
        opC = findViewById(R.id.et_OptionC);
        opD = findViewById(R.id.et_OptionD);
        answer = findViewById(R.id.et_Ans);
        btnSubmit = findViewById(R.id.btn_Submit);
        categoryName = getIntent().getStringExtra("categoryName");

        //Firebase Instance ..........
        databaseReference = FirebaseDatabase.getInstance().getReference().child("quiz");
        databaseReference.keepSynced(true);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qus = question.getText().toString();
                String a = opA.getText().toString();
                String b = opB.getText().toString();
                String c = opC.getText().toString();
                String d = opD.getText().toString();
                String ans = answer.getText().toString();

                QuestionModel questionModel = new QuestionModel(qus,a,b,c,d,ans);
                databaseReference.child("Question").child(categoryName).child(databaseReference.push().getKey()).setValue(questionModel);
                question.setText("");
                opA.setText("");
                opB.setText("");
                opC.setText("");
                opD.setText("");
                answer.setText("");
            }
        });

    }
}