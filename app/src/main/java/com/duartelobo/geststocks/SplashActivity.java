package com.duartelobo.geststocks;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    private int progressStatus = 0;
    private Handler mHandler = new Handler();
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //http://www.compiletimeerror.com/2013/09/android-progress-bar-example.html#.VtHXePKLSUk
        //http://developer.android.com/reference/android/widget/ProgressBar.html
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        final Intent login = new Intent(this, LoginActivity.class);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(5000);
                        startActivity(login);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
