package com.example.glcamerasample;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class CameraGLView extends GLSurfaceView {
    
    CameraRenderer mRenderer;
    
    public CameraGLView(Context context) {
        super(context);
        mRenderer = new CameraRenderer(context);
        setRenderer(mRenderer);
    }
    
}
