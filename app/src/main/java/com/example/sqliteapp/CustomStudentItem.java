package com.example.sqliteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomStudentItem extends BaseAdapter
{
    private List<Student> list;

    public CustomStudentItem(List<Student> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout,null);
        TextView roll_number = v.findViewById(R.id.roll_tv);
        TextView std_name = v.findViewById(R.id.name_tv);
        TextView std_marks = v.findViewById(R.id.marks_tv);

        Student student = list.get(i);
        roll_number.setText(String.valueOf(student.getRoll()));
        std_name.setText(student.getName());
        std_marks.setText(String.valueOf(student.getMarks()));
        return v;
    }
}
