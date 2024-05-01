package kr.ac.tukorea.ge.spgp.gurpaper.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.Warrior;

public class MovingBackground extends Sprite {
    private final float height;
    private final Warrior warrior;
    private float pre_warrior_y;

    public MovingBackground(int bitmapResId, Warrior warrior) {
        super(bitmapResId);
        this.warrior = warrior;
        pre_warrior_y = warrior.y;
        this.height = bitmap.getHeight() * Metrics.width / bitmap.getWidth();
        setPosition(0, 0, Metrics.width, Metrics.height);
    }
    public void update(float elapsedSeconds) {
        this.y += pre_warrior_y-warrior.y;
        pre_warrior_y = warrior.y;
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