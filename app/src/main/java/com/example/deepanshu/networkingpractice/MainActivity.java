package com.example.deepanshu.networkingpractice;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDownloadCompleteListener {

    ListView listView;
    ArrayList<Course> courseArrayList;
    ArrayList<String> courseTitleArrayList;
    ArrayAdapter<String> mStringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        courseArrayList = new ArrayList<>();
        courseTitleArrayList = new ArrayList<>();
        mStringAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,courseTitleArrayList);
        listView.setAdapter(mStringAdapter);
        fetchCourses();
    }

    private void fetchCourses() {

        String urlString = "https://codingninjas.in/api/v1/courses";
        CourseAsyncTask courseAsyncTask = new CourseAsyncTask();
        courseAsyncTask.execute(urlString);
        courseAsyncTask.setOnDownloadCompleteListener(this);
    }

    @Override
    public void setOnDownloadCompleteListener(ArrayList<Course> courses) {
        courseArrayList.clear();
        courseTitleArrayList.clear();
        courseArrayList.addAll(courses);

        for(Course course: courseArrayList){
            String titleObject = course.getTitle();
            courseTitleArrayList.add(titleObject);
        }
        mStringAdapter.notifyDataSetChanged();
    }
}