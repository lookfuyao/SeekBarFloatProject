
SeekBarFloatProject  
=====
An enhanced version of the seekbar can support negative numbers, floating point numbers (maximum precision is 7 bits).
For example 1:<br>
An seekbar Range (-1000.2323232f, 9999.3434343f)<br>
For example 2:<br>
An seekbar Range (500f, 88888f)

Don't call base class methods like:
```
int getMax()
void setProgress(int progress)
...
```
Use those end with "F" instead
```
float getMaxF()
void setProgressF(float progress)
...
```
If you call base class method, you may not set/get right vlaue.

Capture：
-----
<br>![image](https://github.com/lookfuyao/SeekBarFloatProject/blob/master/resource/2017-06-23_17_00_18.gif)

How to use:
-----
Step1:<br>
Add compile at your build.gradle:<br>
compile 'com.fy.seekbarfloat:seekbarfloat:1.0.1'<br>
<br>
Step2<br>
Add SeekBarFloat at xml<br>
```
<com.fy.seekbarex.SeekBarFloat
    android:id="@+id/seekBar"
    style="@style/calibrate_seek_bar_style"
    android:layout_below="@id/text_r"
    seekbarex:seekBarFloat_max="1000.777"
    seekbarex:seekBarFloat_min="50.555"
    seekbarex:seekBarFloat_default_value="10"/>
```
Step3<br>
Add under code at your Activity<br>
```
private SeekBarFloat seekbar;

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    seekbar = (SeekBarFloat) findViewById(R.id.seekBar);
    seekbar.setOnSeekBarChangeListener(onSeekBarChangeListenerEx);
    ...
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

```
End:
----
Feel free to contact me:<br>
lookfuyao@gmail.com<br>
or create an Issues

-----------------
This is my first open source project:)
