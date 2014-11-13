package com.example.glcamerasample;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class SampleSprite {
    
    private int textureNo;
    
    public void setTexture(GL10 gl, Resources res, int id) {
        InputStream is = res.openRawResource(id);
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            return;
        }
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
        
        int[] textureId = new int[1];
        gl.glGenTextures(1, textureId, 0);
        textureNo = textureId[0];
        
        // テクスチャIDのバインド
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNo);
        
        // OpenGL ES用のメモリ領域に画像データを渡す。上でバインドされたテクスチャIDと結び付けられる
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    }
}
