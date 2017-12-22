package com.example.abdul_wahab.uolf17asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyTask(this).execute(2, 34, 5, 2, 2);

    }

    class MyTask extends AsyncTask<Integer, Integer, Boolean> {

        Context context;

        public MyTask(Context context) {
            this.context = context;
            Log.d(TAG, "MyTask: constructor");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Integer... nums) {

            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i % 5 == 0) {
                    publishProgress(i);
                }


                Log.d(TAG, "doInBackground: Task Running " + i);
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Log.d(TAG, "onProgressUpdate: " + values[0]);

            TextView txtProgress = findViewById(R.id.textView);
            txtProgress.setText("Progress " + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean status) {
            super.onPostExecute(status);
            Log.d(TAG, "onPostExecute: ");

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);

            TextView txtProgress = findViewById(R.id.textView);
            txtProgress.setText("Task Completed ");
        }
    }


}
