package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "College";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students_details";


    private static final String ROLL = "Roll";
    private static final String NAME = "Name";
    private static final String MARKS = "Marks";

    private static final String CREATE_QUERY =  " create table " + TABLE_NAME +
            "(" + ROLL + " int primary key," + NAME + " varchar," + MARKS + " real )";


    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME +"");
        onCreate(sqLiteDatabase);
    }
//-----------------------------------------------------------------------------------------------------

    public long insert(Student student)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ROLL",student.getRoll());
        cv.put("NAME",student.getName());
        cv.put("MARKS",student.getMarks());
        return db.insert(TABLE_NAME,null,cv);
    }

//------------------------------------------------------------------------------------------------------

    public List<Student> getAllStudents()
    {
        List<Student> list = new ArrayList<>();
        Cursor cursor = getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            Student student = new Student();
            student.setRoll(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setMarks(cursor.getFloat(2));

            list.add(student);
        }
        return list;
    }

//------------------------------------------------------------------------------------------------------

    public long delete(int roll){
        return getWritableDatabase().delete(TABLE_NAME,""+ ROLL + "=?", new String[]{String.valueOf(roll)});
    }

//------------------------------------------------------------------------------------------------------

    public long update(Student student){
        ContentValues cv = new ContentValues();
        cv.put(ROLL,student.getRoll());
        cv.put(NAME,student.getName());
        cv.put(MARKS,student.getMarks());
        return getWritableDatabase().update(TABLE_NAME,cv,""+ ROLL + "=?", new String[]{String.valueOf(student.getRoll())});
    }

    public Student getStudent(int roll){
        Student student = null;
        Cursor cursor = getReadableDatabase().query(TABLE_NAME,null,"" + ROLL + "=?", new String[]{String.valueOf(roll)},null,null,null,null);
        if (cursor.moveToNext()){
            student = new Student();
            student.setRoll(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setMarks(cursor.getFloat(2));
        }
        return student;
    }
//----------------------------------------------------------------------------------------------------
}
