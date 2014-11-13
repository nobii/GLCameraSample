package com.example.glcamerasample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;

public class CameraRenderer implements Renderer {
    
    private Context mContext;
    private int mCounter;
    
    SampleSprite sprite = new SampleSprite();
    
    public CameraRenderer(Context context) {
        mContext = context;
    }
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mCounter = 0;
        sprite.setTexture(gl, mContext.getResources(), R.raw.texture);
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // GLで利用する領域を指定
        gl.glViewport(0, 0, width, height);
        
        float ratio = (float) height / width;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        
        // 座標系の指定
        gl.glOrthof(-1.0f, 1.0f, -ratio, ratio, 0.5f, -0.5f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.f, 0.f, 1.f, 1.f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        float[] vertices = {
                -0.5f, -0.5f,
                0.5f, -0.5f,
                -0.5f, 0.5f,
                0.5f, 0.5f
        };
        
        FloatBuffer fb = makeFloatBuffer(vertices);
        
        gl.glVertexPointer(2,  GL10.GL_FLOAT, 0, fb);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        
        mCounter++;
        gl.glRotatef((float) mCounter, 0.0f, 0.0f, 1.0f);
        
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }
    
    private static final FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        
        return fb;
    }
}
