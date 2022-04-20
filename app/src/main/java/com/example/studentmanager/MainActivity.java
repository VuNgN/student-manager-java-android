package com.example.studentmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<Student> students = new ArrayList<>();
    int REQUEST_CODE_ADD = new Requests().REQUEST_CODE_ADD;
    int REQUEST_CODE_EDIT = new Requests().REQUEST_CODE_EDIT;
    CustomAdapter adapter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    Common database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CheckBox checkBoxAll = findViewById(R.id.checkbox);
        Button addBtn = findViewById(R.id.addBtn);
        Button editBtn = this.findViewById(R.id.editBtn);
        Button deleteBtn = this.findViewById(R.id.deleteBtn);

        database = new Common(this, "StudentManager.db");
        database.createTable();

        adapter = new CustomAdapter(students, this, editBtn, deleteBtn, checkBoxAll);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentHandleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Request", REQUEST_CODE_ADD);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student checkedStudent = checkedStudents().get(0);
                Intent intent = new Intent(MainActivity.this, StudentHandleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Student", checkedStudent);
                bundle.putInt("Request", REQUEST_CODE_EDIT);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                showAlertDialog(MainActivity.this);
            }
        });

        getData();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Xử lý sự kiện thêm mới sinh viên
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            Student newStudent = (Student) data.getSerializableExtra("Student");
            database.insertData(newStudent.getName(), newStudent.getAge(), newStudent.getSex());
            getData();
        }
        // Xử lý sự kiện sửa sinh viên
        else if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            Student editedStudent = (Student) data.getSerializableExtra("Student");
            database.updateData(editedStudent.getId(), editedStudent.getName(), editedStudent.getAge(), editedStudent.getSex());
            getData();
        }
    }

    /**
     * Trả về các phần tử của recycler đã được check
     */
    private ArrayList<Student> checkedStudents() {
        ArrayList<Student> checkedStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isChecked()) {
                checkedStudents.add(students.get(i));
            }
        }
        return checkedStudents;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getData() {
        Cursor studentsCursor = database.selectData();
        students.clear();
        while (studentsCursor.moveToNext()) {
            Integer id = studentsCursor.getInt(0);
            String name = studentsCursor.getString(1);
            int age = studentsCursor.getInt(2);
            String sex = studentsCursor.getString(3);
            students.add(new Student(id, name, age, sex));
        }
        adapter.notifyDataSetChanged();
    }

    public void showAlertDialog(final Context context) {
        final Drawable positiveIcon = context.getResources().getDrawable(R.drawable.ic_baseline_check_circle_24);
        final Drawable negativeIcon = context.getResources().getDrawable(R.drawable.ic_baseline_cancel_24);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Hey dude!").setMessage("Do you want to delete?");


        builder.setCancelable(true);
        // builder.setIcon(R.drawable.icon_title);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            public void onClick(DialogInterface dialog, int id) {
                database.deleteStudents(checkedStudents());
                getData();
                adapter.btnHandle();
            }
        });
        builder.setPositiveButtonIcon(positiveIcon);

        // Create "No" button with OnClickListener.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButtonIcon(negativeIcon);

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }

}