package com.queueit.quizadminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.queueit.quizadminpanel.Adapter.CategoryAdapter;
import com.queueit.quizadminpanel.ModelClass.GetCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    EditText category;
    LinearLayout btnSubmit;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    CategoryAdapter categoryAdapter;
    List<GetCategoryModel> getCategoryModels = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("quiz");
        category = findViewById(R.id.et_Category);
        btnSubmit = findViewById(R.id.btn_Submit);

        categoryAdapter = new CategoryAdapter(this,getCategoryModels);

        recyclerView = findViewById(R.id.rv_Category);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Category = category.getText().toString();
                if (Category.isEmpty()){
                    category.setError("Field is required");
                    category.requestFocus();
                    return;
                }else {
                    GetCategoryModel getCategoryModel = new GetCategoryModel(Category,databaseReference.push().getKey());
                    databaseReference.child("Category").child(databaseReference.push().getKey()).setValue(getCategoryModel);
                    category.setText("");
                    Toast.makeText(CategoryActivity.this, "Category Add Successfully", Toast.LENGTH_SHORT).show();
                }
             }
        });

        getCategory();
    }

    private void getCategory()
    {
        databaseReference.child("Category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getCategoryModels.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    GetCategoryModel getCategoryModel = dataSnapshot.getValue(GetCategoryModel.class);
                    getCategoryModels.add(getCategoryModel);
                }
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("error",error.getMessage());
            }
        });
    }
}