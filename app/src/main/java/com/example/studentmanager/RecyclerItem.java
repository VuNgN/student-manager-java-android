package com.example.studentmanager;

/**
 * Khai báo đối tượng các item của recycler list
 * id là id của item
 * student là Student
 * isChecked là trạng thái của item
 * */
public class RecyclerItem {
    private final int id;
    private final Student student;
    private boolean isChecked;

    public RecyclerItem(int id, Student student, boolean isChecked) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}
