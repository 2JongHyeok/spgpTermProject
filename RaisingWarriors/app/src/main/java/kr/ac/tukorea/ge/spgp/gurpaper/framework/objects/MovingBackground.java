package kr.ac.tukorea.ge.spgp.gurpaper.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.Warrior;

public class MovingBackground extends Sprite {
    private final float height;
    public MovingBackground(int bitmapResId) {
        super(bitmapResId);
        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        setPosition(Metrics.width / 2, Metrics.height / 2, Metrics.width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        float curr = y % height;
        if (curr > 0) curr -= height;
        while (curr < Metrics.height) {
            dstRect.set(0, curr, Metrics.width, curr + height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += height;
        }
    }
}