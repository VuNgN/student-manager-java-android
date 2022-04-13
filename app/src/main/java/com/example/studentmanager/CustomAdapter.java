package com.example.studentmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final ArrayList<RecyclerItem> recyclerItems;
    private Context context;
    private final Button editBtn;
    private final Button deleteBtn;
    private final CheckBox checkBoxAll;

    public CustomAdapter(ArrayList<RecyclerItem> recyclerItems, Context context, Button editBtn, Button deleteBtn, CheckBox checkBoxAll) {
        this.recyclerItems = recyclerItems;
        this.context = context;
        this.editBtn = editBtn;
        this.deleteBtn = deleteBtn;
        this.checkBoxAll = checkBoxAll;
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
        eventHandler(holder, recyclerItem);
        checkBoxHandle();
        btnHandle();
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
    /**
     * Hàm xử lý cho các button
     * Khi người dùng check 1 checkbox thì có thể sửa và xoá và thêm mới
     * Khi người dùng check nhiều hơn 1 checkbox thì chỉ có thể xoá và thêm mới
     * Khi người dùng không check checkbox nào thì chỉ có thể thêm mới
     * */
    public void btnHandle() {
        int count = 0;
        for (int i = 0; i < recyclerItems.size(); i++) {
            if (recyclerItems.get(i).getIsChecked()) {
                ++count;
            }
        }
        if(count == 1) {
            editBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        } else if (count > 1) {
            editBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            editBtn.setVisibility(View.GONE);
            deleteBtn.setVisibility(View.GONE);
        }
    }

    /**
     * Hàm xử lý checkbox tất cả trên title */
    private void checkBoxHandle() {
        checkBoxAll.setChecked(isCheckedAll());
    }
    /**
     * Hàm trả về true nếu tất cả các checkbox đã được check
     * Ngược lại trả về false
     * */
    private boolean isCheckedAll () {
        for (int i = 0; i < recyclerItems.size(); i++) {
            if (!recyclerItems.get(i).getIsChecked())
                return false;
        }
        return recyclerItems.size() > 0;
    }

    /**
     * Hàm xử lý các sự kiện
     * */
    private void eventHandler(MyViewHolder holder, RecyclerItem recyclerItem) {
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                recyclerItem.setChecked(b);
                btnHandle();
                checkBoxHandle();
            }
        });
        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                for (int i = 0; i < recyclerItems.size(); i++) {
                    recyclerItems.get(i).setChecked(checkBoxAll.isChecked());
                }
                btnHandle();
                notifyDataSetChanged();
            }
        });
    }
}
