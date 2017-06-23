package com.fy.seekbar.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.fy.seekbarex.SeekBarFloat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView radiusTextView;
    private TextView offsetXTextView;
    private TextView offsetYTextView;
    private SeekBarFloat seekbar;
    private SeekBarFloat seekbar3;
    private SeekBarFloat seekbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiusTextView = (TextView) findViewById(R.id.text_r);
        offsetXTextView = (TextView) findViewById(R.id.text_offset_x);
        offsetYTextView = (TextView) findViewById(R.id.text_offset_y);
        seekbar = (SeekBarFloat) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(onSeekBarChangeListenerEx);
        seekbar3 = (SeekBarFloat) findViewById(R.id.seekBar3);
        seekbar3.setOnSeekBarChangeListener(onSeekBarChangeListenerEx);
        seekbar4 = (SeekBarFloat) findViewById(R.id.seekBar4);
        seekbar4.setOnSeekBarChangeListener(onSeekBarChangeListenerEx);
    }

    private SeekBarFloat.OnSeekBarChangeListener onSeekBarChangeListenerEx = new SeekBarFloat.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBarFloat seekBarFloat, float progress, boolean fromUser) {
            switch (seekBarFloat.getId()) {
                case R.id.seekBar:
                    radiusTextView.setText("radius: " + progress);
                    break;
                case R.id.seekBar3:
                    offsetXTextView.setText("offsetX: " + progress);
                    break;
                case R.id.seekBar4:
                    offsetYTextView.setText("offsetX: " + progress);
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBarFloat seekBarFloat) {
            Log.d(TAG, "onStartTrackingTouch");
        }

        @Override
        public void onStopTrackingTouch(SeekBarFloat seekBarFloat) {
            switch (seekBarFloat.getId()) {
                case R.id.seekBar:
                    radiusTextView.setText("radius : " + seekBarFloat.getProgressF());
                    break;
                case R.id.seekBar3:
                    offsetXTextView.setText("offsetX : " + seekBarFloat.getProgressF());
                    break;
                case R.id.seekBar4:
                    offsetYTextView.setText("offsetX : " + seekBarFloat.getProgressF());
                    break;
            }
        }
    };


}
