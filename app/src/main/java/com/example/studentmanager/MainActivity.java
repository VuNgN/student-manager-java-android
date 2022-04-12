package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<RecyclerItem> recyclerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CheckBox checkBoxAll = findViewById(R.id.checkbox);
        Button addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerItems.add(new RecyclerItem(new Student("Nguyễn Ngọc Vũ", 21, "Nam"), false));
        recyclerItems.add(new RecyclerItem(new Student("Jack Ma", 57, "Nam"), false));
        recyclerItems.add(new RecyclerItem(new Student("Mark Elliot Zuckerberg", 37, "Nam"), false));
        recyclerItems.add(new RecyclerItem(new Student("Justin Bieber", 28, "Nam"), false));
        recyclerItems.add(new RecyclerItem(new Student("Elon Musk", 50, "Nam"), false));

        CustomAdapter adapter = new CustomAdapter(recyclerItems, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        checkBoxAll.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    for (int i = 0; i < recyclerItems.size(); i++) {
                        recyclerItems.get(i).setChecked(b);
                    }
                    recyclerView.setAdapter(adapter);
                }
            }
        );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Intent intent = getIntent();
//        recyclerItems.add(new RecyclerItem((Student) intent.getSerializableExtra("Student"), false));
//        Log.d("AAAAAA", "" + recyclerItems.size());
    }
}