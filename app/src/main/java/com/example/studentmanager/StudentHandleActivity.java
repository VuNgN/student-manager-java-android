package com.example.studentmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentHandleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_handle);
        TextView title = findViewById(R.id.titleText);
        Spinner sexDropdown = findViewById(R.id.sexDropDown);
        EditText nameInput = findViewById(R.id.editTextTextPersonName);
        EditText ageInput = findViewById(R.id.editTextTextPersonAge);
        Button cancelBtn = findViewById(R.id.cancel_button);
        Button handleBtn = findViewById(R.id.add_button);
        int REQUEST_CODE_ADD = new Requests().REQUEST_CODE_ADD;
        int REQUEST_CODE_EDIT = new Requests().REQUEST_CODE_EDIT;

        Intent getIntent = getIntent();
        int REQUEST = getIntent.getIntExtra("Request", 0);

        //create a list of items for the spinner.
        String[] items = new String[]{"Nam", "Nữ"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        sexDropdown.setAdapter(adapter);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentHandleActivity.this.finish();
            }
        });
        // Nếu người dùng muốn thêm Student
        if (REQUEST == REQUEST_CODE_ADD) {
            handleBtn.setText(R.string.addBtn);
            title.setText(R.string.add_a_student);
            handleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendData(nameInput, ageInput, sexDropdown);
                }
            });
        }
        // Nếu người dùng muốn sửa Student
        else if (REQUEST == REQUEST_CODE_EDIT) {
            handleBtn.setText(R.string.editBtn);
            title.setText(R.string.edit_student);
            Student student = (Student) getIntent.getSerializableExtra("Student");
            nameInput.setText(student.getName());
            ageInput.setText(String.valueOf(student.getAge()));
            sexDropdown.setSelection(adapter.getPosition(student.getSex()));
            handleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendData(nameInput, ageInput, sexDropdown);
                }
            });
        }
    }

    /**
     * Hàm gửi dữ liệu ra activity khác
     * */
    private void sendData(EditText nameInput, EditText ageInput, Spinner sexDropdown) {
        String name = nameInput.getText().toString().trim();
        String age = ageInput.getText().toString().trim();
        String sex = sexDropdown.getSelectedItem().toString().trim();

        if (!name.equals("") && !age.equals("") && !sex.equals("")) {
            Student student = new Student(name, Integer.parseInt(age), sex);
            Intent intent = new Intent();
            intent.putExtra("Student", student);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}