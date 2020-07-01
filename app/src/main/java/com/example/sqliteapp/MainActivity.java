package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, roll, marks;
    Button insertBTN;
    SQLiteHelper database;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_textView);
        roll = findViewById(R.id.roll_textView);
        marks = findViewById(R.id.marks_textView);
        insertBTN = findViewById(R.id.insert_button);
        database = new SQLiteHelper(this);
        listView = findViewById(R.id.listview);

    }

    //------------------------------------------------------------------------------------------------------
    public void insertData(View view) {
        String roll_no = roll.getText().toString();
        String student_name = name.getText().toString();
        String student_marks = marks.getText().toString();

        Student student = new Student();

        student.setRoll(Integer.parseInt(roll_no));
        student.setName(student_name);
        student.setMarks(Float.parseFloat(student_marks));


        long t = database.insert(student);
        if (t > 0)
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show();
        roll.setText("");
        name.setText("");
        marks.setText("");
    }

    //------------------------------------------------------------------------------------------------------
    public void fetch(View view) {
        List<Student> list = database.getAllStudents();
        if (list.size() > 0)
            listView.setAdapter(new CustomStudentItem(list));
        else
            Toast.makeText(this, "No Data Present! Please Add Data", Toast.LENGTH_SHORT).show();
    }

    //------------------------------------------------------------------------------------------------------
    public void update(View view) {
        String roll_no = roll.getText().toString();
        Student student = database.getStudent(Integer.parseInt(roll_no));
        if (student != null) {
            student.setName(name.getText().toString());
            student.setMarks(Float.parseFloat(marks.getText().toString()));
            long t = database.update(student);
            if (t > 0)
                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Invalid Roll Number", Toast.LENGTH_LONG).show();
    }

    public void delete(View view) {
        String roll_no = roll.getText().toString();
        Student student = database.getStudent(Integer.parseInt(roll_no));
        if (student != null) {
            long t = database.delete(Integer.parseInt(roll_no));
            if (t > 0)
                Toast.makeText(this, "Record Deleted Successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Error Occurred : Deletion failed", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Invalid Roll Number", Toast.LENGTH_SHORT).show();
    }
}