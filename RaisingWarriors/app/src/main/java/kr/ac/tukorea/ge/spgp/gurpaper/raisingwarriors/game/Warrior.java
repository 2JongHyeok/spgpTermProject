package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;

public class Warrior extends Sprite {
    private static final float WARRIOR_WIDTH = 1.f;
    private static final float WARRIOR_HEIGHT = WARRIOR_WIDTH * 259/181;
    private static final float RADIUS = 1.0f;
    private static final float SPEED = 3.0f;
    private final JoyStick joyStick;
    private float angle;

    public Warrior(JoyStick joyStick) {
        super(R.mipmap.charactor);
        x = Metrics.width / 2;
        y = 2 * Metrics.height / 3;
        angle = -90;
        setPosition(x-RADIUS, y,WARRIOR_WIDTH, WARRIOR_HEIGHT);
        this.joyStick = joyStick;
    }

    @Override
    public void update(float elapsedSeconds) {
        if (joyStick.power > 0) {
            float distance = SPEED * joyStick.power * elapsedSeconds;
            x += (float) (distance * Math.cos(joyStick.angle_radian));
            y += (float) (distance * Math.sin(joyStick.angle_radian));
            dstRect.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
            angle = (float) Math.toDegrees(joyStick.angle_radian);
        }
        super.update(elapsedSeconds);
    }
    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
    }
}


