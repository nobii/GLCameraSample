package com.example.glcamerasample;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    
    CameraGLView mGlView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGlView = new CameraGLView(this);
        // setContentView(R.layout.activity_main);
        setContentView(mGlView);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mGlView.onResume();
    }
    
    
    @Override
    protected void onPause() {
        super.onPause();
        mGlView.onPause();
    }
}
