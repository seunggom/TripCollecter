package com.seunggom.tripmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    Context mContext;
    SurfaceHolder mHolder;
    RenderingThread mRThread;
    //SThread thread;




    public MySurfaceView(Context context) {
        super(context);
        mContext = context;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mRThread = new MySurfaceView.RenderingThread();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Surface가 만들어질 때 호출됨
        mRThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        // Surface가 변경될 때 호출됨
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Surface가 종료될 때 호출됨
        try {
            mRThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class RenderingThread extends Thread {
        Bitmap img_android;
        Bitmap a;
        public RenderingThread() {
            img_android = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.map_0_daegu);
            a = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.map_0_seoul);
        }

        public void run() {
            Canvas canvas = null;
            while (true) {
                canvas = mHolder.lockCanvas();
                try {
                    synchronized (mHolder) {
                        canvas.drawBitmap(img_android, getMatrix(), null);
                        canvas.drawBitmap(a, getMatrix(), null);
                    }
                } finally {
                    if (canvas == null) return;
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    } // RenderingThread


}
