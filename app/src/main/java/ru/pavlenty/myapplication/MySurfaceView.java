package ru.pavlenty.myapplication;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class MySurfaceView
        extends SurfaceView
        implements SurfaceHolder.Callback {

    MyThread mt;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mt = new MyThread(getHolder());
        mt.setRunning(true);
        mt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        mt.setRunning(false);
        while(retry) {
            try {
                mt.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
