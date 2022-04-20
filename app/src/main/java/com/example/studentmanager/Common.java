package com.example.studentmanager;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Common {
    private final Context context;
    private final String name;
    Database database;

    public Common(Context context, String name) {
        this.context = context;
        this.name = name;
        database = new Database(context, name, null, 1);
    }

    public Context getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public void createTable() {
        database.execQuery("CREATE TABLE IF NOT EXISTS Student (Id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, Name VARCHAR(255), Age INTEGER, Sex VARCHAR(50))");
    }

    /**
     * Hàm xử lý xoá Students trong database
     */
    public void deleteStudents(ArrayList<Student> checkedStudents) {
        for (int i = checkedStudents.size(); i > 0; i--) {
            String query = "DELETE FROM Student WHERE Id = " + checkedStudents.get(i - 1).getId() + "";
            database.execQuery(query);
        }
    }

    /**
     * Hàm xử lý thêm Students vào trong database
     */
    public void insertData(String name, int age, String sex) {
        String query = "INSERT INTO Student VALUES (null, '" + name + "', " + age + ", '" + sex + "')";
        database.execQuery(query);
    }

    /**
     * Hàm xử lý cập nhật Students trong database
     */
    public void updateData(Integer id, String name, int age, String sex) {
        String query = "UPDATE Student SET Name = '" + name + "', Age = " + age + ", Sex = '" + sex + "' WHERE Id = " + id + "";
        database.execQuery(query);
    }

    public Cursor selectData() {
        String query = "SELECT * FROM Student";
        return database.getData(query);
    }
}
