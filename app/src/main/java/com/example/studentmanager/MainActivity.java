package com.example.studentmanager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();
    int REQUEST_CODE_ADD = new Requests().REQUEST_CODE_ADD;
    int REQUEST_CODE_EDIT = new Requests().REQUEST_CODE_EDIT;
    CustomAdapter adapter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CheckBox checkBoxAll = findViewById(R.id.checkbox);
        Button addBtn = findViewById(R.id.addBtn);
        Button editBtn = this.findViewById(R.id.editBtn);
        Button deleteBtn = this.findViewById(R.id.deleteBtn);

        adapter = new CustomAdapter(recyclerItems, this, editBtn, deleteBtn, checkBoxAll);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentHandleActivity.class);
                intent.putExtra("Request", REQUEST_CODE_ADD);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student checkedStudent = checkedStudents().get(0).getStudent();
                Intent intent = new Intent(MainActivity.this, StudentHandleActivity.class);
                intent.putExtra("Student", (Serializable) checkedStudent);
                intent.putExtra("Request", REQUEST_CODE_EDIT);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        recyclerItems.add(new RecyclerItem(0, new Student("Nguyễn Ngọc Vũ", 21, "Nam"), false));
        recyclerItems.add(new RecyclerItem(1, new Student("Jack Ma", 57, "Nam"), false));
        recyclerItems.add(new RecyclerItem(2, new Student("Mark Elliot Zuckerberg", 37, "Nam"), false));
        recyclerItems.add(new RecyclerItem(3, new Student("Justin Bieber", 28, "Nam"), false));
        recyclerItems.add(new RecyclerItem(4, new Student("Taylor Swift", 32, "Nữ"), false));
        recyclerItems.add(new RecyclerItem(5, new Student("Elon Musk", 50, "Nam"), false));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Xử lý sự kiện thêm mới sinh viên
        if(requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            Student newStudent = (Student) data.getSerializableExtra("Student");
            recyclerItems.add(new RecyclerItem(recyclerItems.size(), newStudent, false));
            adapter.notifyDataSetChanged();
        }
        // Xử lý sự kiện sửa sinh viên
        else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            RecyclerItem checkedRecyclerItem = checkedStudents().get(0);
            Student editedStudent = (Student) data.getSerializableExtra("Student");
            for (int i = 0; i < recyclerItems.size(); i++){
                RecyclerItem recyclerItem = recyclerItems.get(i);
                if (recyclerItem.getId() == checkedRecyclerItem.getId()) {
                    recyclerItem.getStudent().setName(editedStudent.getName());
                    recyclerItem.getStudent().setAge(editedStudent.getAge());
                    recyclerItem.getStudent().setSex(editedStudent.getSex());
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     *  Trả về các phần tử của recycler đã được check
     */
    private ArrayList<RecyclerItem> checkedStudents(){
        ArrayList<RecyclerItem> checkedRecyclerItems = new ArrayList<>();
        for (int i = 0; i < recyclerItems.size(); i++) {
            if (recyclerItems.get(i).getIsChecked()) {
                checkedRecyclerItems.add(recyclerItems.get(i));
            }
        }
        return checkedRecyclerItems;
    }
}