package com.example.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final ArrayList<RecyclerItem> recyclerItems;
    private Context context;

    public CustomAdapter(ArrayList<RecyclerItem> recyclerItems, Context context) {
        this.recyclerItems = recyclerItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View studentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerItem recyclerItem = recyclerItems.get(position);
        holder.getNameTextView().setText(recyclerItem.getStudent().getName());
        holder.getAgeTextView().setText(String.valueOf(recyclerItem.getStudent().getAge()));
        holder.getSexTextView().setText(recyclerItem.getStudent().getSex());
        holder.getCheckBox().setChecked(recyclerItem.getIsChecked());
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView ageTextView;
        private final TextView sexTextView;
        private final CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            sexTextView = itemView.findViewById(R.id.sexTextView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

        public TextView getNameTextView() {
            return this.nameTextView;
        }

        public TextView getAgeTextView() {
            return ageTextView;
        }

        public TextView getSexTextView() {
            return sexTextView;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }
}
