package kr.ac.tukorea.ge.spgp.gurpaper.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class MainPage extends Sprite {
    public MainPage(int bitmapResId) {
        super(bitmapResId);
        setPosition(0, 0, Metrics.width, Metrics.height);
    }
    @Override
    public void draw(Canvas canvas) {
            dstRect.set(0, 0,width,  height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}