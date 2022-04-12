package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Spinner dropdown = findViewById(R.id.sexDropDown);
        EditText nameInput = findViewById(R.id.editTextTextPersonName);
        EditText ageInput = findViewById(R.id.editTextTextPersonAge);
        Button cancelBtn = findViewById(R.id.cancel_button);
        Button addBtn = findViewById(R.id.add_button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddActivity.this.finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();
                String age = ageInput.getText().toString().trim();
                String sex = dropdown.getSelectedItem().toString().trim();

                if (!name.equals("") && !age.equals("") && !sex.equals("")) {
                    Student student = new Student(name, Integer.parseInt(age), sex);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.putExtra("Student", student);
                    startActivity(intent);
                }
            }
        });

        //create a list of items for the spinner.
        String[] items = new String[]{"Nam", "Nữ"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}