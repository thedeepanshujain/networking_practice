package com.example.deepanshu.networkingpractice;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by deepanshu on 26/7/17.
 */

public class CourseAsyncTask extends AsyncTask<String,Void,ArrayList<Course>> {

    OnDownloadCompleteListener mListener;

    void setOnDownloadCompleteListener(OnDownloadCompleteListener listener){
        this.mListener = listener;
    }

    @Override
    protected ArrayList<Course> doInBackground(String... params) {

        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String str = "";
            while (scanner.hasNext()){
                str += scanner.nextLine();
            }

            return parsedCourses(str);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Course> parsedCourses(String str) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray courseJSONArray = data.getJSONArray("courses");

            ArrayList<Course> courseArrayList = new ArrayList<>();

            for(int i=0;i<courseJSONArray.length();i++){
                JSONObject courseObject = courseJSONArray.getJSONObject(i);
                int id = courseObject.getInt("id");
                String title = courseObject.getString("title");
                String name = courseObject.getString("name");
                boolean isActive = courseObject.getBoolean("is_active");
                String overview = courseObject.getString("overview");
                int feeWithTaxes = courseObject.getInt("fee_with_taxes");

                Course toInsert = new Course(id,title,name,isActive,overview,feeWithTaxes);
                courseArrayList.add(toInsert);
            }

            return courseArrayList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Course> courses) {

        if(mListener!=null){
            mListener.setOnDownloadCompleteListener(courses);
        }
    }
}

interface OnDownloadCompleteListener{

    void setOnDownloadCompleteListener(ArrayList<Course> courses);
}