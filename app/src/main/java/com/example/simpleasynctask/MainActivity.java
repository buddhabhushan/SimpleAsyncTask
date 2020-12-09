package com.example.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgrssBar;

    private static final String TEXT_STATE = "currentState";
    private static final String PROGRESS_STATE = "progressState";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mProgrssBar = findViewById(R.id.progress_bar);

        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgrssBar.setProgress(savedInstanceState.getInt(PROGRESS_STATE));
        }
    }

    public void startTask(View view) {
        // Assign the value to the textview
        mTextView.setText(R.string.napping);

        // Start the AsyncTask
        new SimpleAsyncTask(mTextView, mProgrssBar).execute();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putInt(PROGRESS_STATE, mProgrssBar.getProgress());
    }
}