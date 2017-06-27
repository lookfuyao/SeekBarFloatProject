package com.fy.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.fy.seekbarex.SeekBarFloat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int MSG_UPDATE_CALIBRATE_UI = 5;

    private static final int MSG_VIEW_SINGLE_PRESS = 10;
    private static final int MSG_VIEW_LONG_PRESS = 11;
    private static final int MSG_VIEW_REPEAT_PRESS = 12;


    private TextView radiusTextView;
    private TextView offsetXTextView;
    private TextView offsetYTextView;
    private SeekBarFloat seekbar;
    private SeekBarFloat seekbar3;
    private SeekBarFloat seekbar4;
    private MainHandler mMainHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainHandler = new MainHandler();
        initCalibrate();
    }


    private void initCalibrate() {
        radiusTextView = (TextView) findViewById(R.id.text_r);
        offsetXTextView = (TextView) findViewById(R.id.text_offset_x);
        offsetYTextView = (TextView) findViewById(R.id.text_offset_y);
        seekbar = (SeekBarFloat) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbar3 = (SeekBarFloat) findViewById(R.id.seekBar3);
        seekbar3.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekbar4 = (SeekBarFloat) findViewById(R.id.seekBar4);
        seekbar4.setOnSeekBarChangeListener(onSeekBarChangeListener);

        findViewById(R.id.inc_button).setOnTouchListener(touchUpdateListener);
        findViewById(R.id.dec_button).setOnTouchListener(touchUpdateListener);
        findViewById(R.id.inc_button3).setOnTouchListener(touchUpdateListener);
        findViewById(R.id.dec_button3).setOnTouchListener(touchUpdateListener);
        findViewById(R.id.inc_button4).setOnTouchListener(touchUpdateListener);
        findViewById(R.id.dec_button4).setOnTouchListener(touchUpdateListener);

        updateCalibrate();
    }


    private View.OnTouchListener touchUpdateListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d(TAG, "onTouch " + event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mMainHandler.sendMessageDelayed(mMainHandler.obtainMessage(MSG_VIEW_SINGLE_PRESS, v), 10);
                    mMainHandler.sendMessageDelayed(mMainHandler.obtainMessage(MSG_VIEW_LONG_PRESS, v), 500);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mMainHandler.removeMessages(MSG_VIEW_SINGLE_PRESS);
                    mMainHandler.removeMessages(MSG_VIEW_LONG_PRESS);
                    mMainHandler.removeMessages(MSG_VIEW_REPEAT_PRESS);
                    break;
            }
            return true;
        }
    };

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            Log.d(TAG, "handleMessage " + msg.what);
            switch (msg.what) {

                case MSG_UPDATE_CALIBRATE_UI:
                        radiusTextView.setText("radius:" + seekbar.getProgressF());
                        offsetXTextView.setText("offset X:" + seekbar3.getProgressF());
                        offsetYTextView.setText("offset Y:" + seekbar4.getProgressF());
//                    seekbar.setProgressF(mVrManager.getRadius());
//                    seekbar3.setProgressF(mVrManager.getOffsetX());
//                    seekbar4.setProgressF(mVrManager.getOffsetY());
                    break;


                case MSG_VIEW_SINGLE_PRESS:
                    updateProgress((View) msg.obj);
                    break;

                case MSG_VIEW_LONG_PRESS:
                    updateProgress((View) msg.obj, 0.01f);
                    sendMessageDelayed(obtainMessage(MSG_VIEW_REPEAT_PRESS, msg.obj), 10);
                    break;

                case MSG_VIEW_REPEAT_PRESS:
                    updateProgress((View) msg.obj, 0.01f);
                    sendMessageDelayed(obtainMessage(MSG_VIEW_REPEAT_PRESS, msg.obj), 100);
                    break;
            }
        }
    }


    private void updateProgress(View view, float interval) {
        switch (view.getId()) {
            case R.id.inc_button:
                updateProgress(seekbar, interval, true);
                break;
            case R.id.dec_button:
                updateProgress(seekbar, interval, false);
                break;
            case R.id.inc_button3:
                updateProgress(seekbar3, interval, true);
                break;
            case R.id.dec_button3:
                updateProgress(seekbar3, interval, false);
                break;
            case R.id.inc_button4:
                updateProgress(seekbar4, interval, true);
                break;
            case R.id.dec_button4:
                updateProgress(seekbar4, interval, false);
                break;
        }
    }

    private void updateProgress(View view) {
        updateProgress(view, 0.001f);
    }

    private void updateProgress(SeekBarFloat seekbar, float interval, boolean add) {
        float max = seekbar.getMaxF();
        float min = seekbar.getMinF();
        float progress = seekbar.getProgressF();
        Log.d(TAG, "updateProgress " + interval + " add " + add + " max = " + max + " min =" + min + " progress " + progress);
        if (add) {
            if (progress < max) {
                progress += interval;
            }

            if (progress > max) {
                progress = max;
            }
        } else {
            if (progress > min) {
                progress -= interval;
            }
            if (progress < min) {
                progress = min;
            }
        }
        Log.d(TAG, "updateProgress " + progress);
        seekbar.setProgressF(progress);
        updateCalibrate();
    }


    private SeekBarFloat.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBarFloat.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBarFloat seekBar, float progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBarFloat seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBarFloat seekBar) {
            updateCalibrate();
        }
    };

    private void updateCalibrate() {
        if (!mMainHandler.hasMessages(MSG_UPDATE_CALIBRATE_UI)) {
            mMainHandler.sendEmptyMessage(MSG_UPDATE_CALIBRATE_UI);
        }
    }

}
