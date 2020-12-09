package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView textView, ProgressBar progressBar){
        mTextView = new WeakReference<>(textView);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 2000;
        for(int time = 0; time < 100; time++){
            try {
                Thread.sleep(s/100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            publishProgress((time));
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTextView.get().setText(s);
    }

    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.get().setProgress(values[0]);
    }
}
