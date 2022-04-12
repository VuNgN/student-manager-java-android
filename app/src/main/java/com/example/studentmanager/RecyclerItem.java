package com.example.studentmanager;

public class RecyclerItem {
    private final Student student;
    private boolean isChecked;

    public RecyclerItem(Student student, boolean isChecked) {
        this.student = student;
        this.isChecked = isChecked;
    }

    public Student getStudent() {
        return student;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getIsChecked() {
        return isChecked;
    }
}
