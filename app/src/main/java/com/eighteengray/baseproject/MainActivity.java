package com.eighteengray.baseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eighteengray.calendar.SampleTimesSquareActivity;
import com.eighteengray.coordinator.CoordinatorActivity;
import com.eighteengray.progressbar.ProgressBarActivity;
import com.eighteengray.textview.TextViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.coordinator)
    TextView coordinator;
    @BindView(R.id.progressbar)
    TextView progressbar;
    @BindView(R.id.calendar)
    TextView calendar;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.timer)
    TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
//        coordinator.setText(stringFromJNI());
    }

    private void initView(){

        coordinator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoordinatorActivity.class);
                startActivity(intent);
            }
        });

        progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProgressBarActivity.class);
                startActivity(intent);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleTimesSquareActivity.class);
                startActivity(intent);
            }
        });

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
                startActivity(intent);
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoordinatorActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
