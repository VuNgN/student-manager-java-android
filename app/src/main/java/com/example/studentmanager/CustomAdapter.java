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
    private final ArrayList<Student> students;
    private Context context;
    private final Button editBtn;
    private final Button deleteBtn;
    private final CheckBox checkBoxAll;

    public CustomAdapter(ArrayList<Student> students, Context context, Button editBtn, Button deleteBtn, CheckBox checkBoxAll) {
        this.students = students;
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
        Student student = students.get(position);
        holder.getNameTextView().setText(student.getName());
        holder.getAgeTextView().setText(String.valueOf(student.getAge()));
        holder.getSexTextView().setText(student.getSex());
        holder.getCheckBox().setChecked(student.isChecked());
        eventHandler(holder, student);
        checkBoxHandle();
        btnHandle();
    }

    @Override
    public int getItemCount() {
        return students.size();
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
     * H??m x??? l?? cho c??c button
     * Khi ng?????i d??ng check 1 checkbox th?? c?? th??? s???a v?? xo?? v?? th??m m???i
     * Khi ng?????i d??ng check nhi???u h??n 1 checkbox th?? ch??? c?? th??? xo?? v?? th??m m???i
     * Khi ng?????i d??ng kh??ng check checkbox n??o th?? ch??? c?? th??? th??m m???i
     */
    public void btnHandle() {
        int count = 0;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isChecked()) {
                ++count;
            }
        }
        if (count == 1) {
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
     * H??m x??? l?? checkbox t???t c??? tr??n title
     */
    private void checkBoxHandle() {
        checkBoxAll.setChecked(isCheckedAll());
    }

    /**
     * H??m tr??? v??? true n???u t???t c??? c??c checkbox ???? ???????c check
     * Ng?????c l???i tr??? v??? false
     */
    private boolean isCheckedAll() {
        for (int i = 0; i < students.size(); i++) {
            if (!students.get(i).isChecked())
                return false;
        }
        return students.size() > 0;
    }

    /**
     * H??m x??? l?? c??c s??? ki???n
     */
    private void eventHandler(MyViewHolder holder, Student student) {
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                student.setChecked(b);
                btnHandle();
                checkBoxHandle();
            }
        });
        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                for (int i = 0; i < students.size(); i++) {
                    students.get(i).setChecked(checkBoxAll.isChecked());
                }
                btnHandle();
                notifyDataSetChanged();
            }
        });
    }
}
