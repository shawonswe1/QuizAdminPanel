package com.queueit.quizadminpanel.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.queueit.quizadminpanel.MainActivity;
import com.queueit.quizadminpanel.ModelClass.GetCategoryModel;
import com.queueit.quizadminpanel.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<GetCategoryModel> getCategoryModels;

    public CategoryAdapter(Context context, List<GetCategoryModel> getCategoryModels) {
        this.context = context;
        this.getCategoryModels = getCategoryModels;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_sample_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        GetCategoryModel getCategoryModel = getCategoryModels.get(position);
        holder.categoryName.setText(getCategoryModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("categoryName",getCategoryModel.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCategoryModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
