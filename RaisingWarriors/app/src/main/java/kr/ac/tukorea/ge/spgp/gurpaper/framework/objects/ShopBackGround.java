package kr.ac.tukorea.ge.spgp.gurpaper.framework.objects;

import android.graphics.Canvas;
import android.graphics.Matrix;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class ShopBackGround  extends Sprite {
    public ShopBackGround(int bitmapResId) {
        super(bitmapResId);
    }

    @Override
    public void draw(Canvas canvas) {
        dstRect.set(0, 0, 100, 100);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
