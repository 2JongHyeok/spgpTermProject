package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;

public class Bullet extends Sprite implements IBoxCollidable, IRecyclable {
    private static final float BULLET_WIDTH = 0.68f;
    private static final float BULLET_HEIGHT = BULLET_WIDTH * 40 / 28;
    private static final float SPEED = 20.0f;
    private int power;

    private Bullet(float x, float y, int power) {
        super(R.mipmap.gonggal);
        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.power = power;
        dy = -SPEED;
    }
    public static Bullet get(float x, float y, int power) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
            bullet.power = power;
            return bullet;
        }
        return new Bullet(x, y, power);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.bottom < 0) {
            Scene.top().remove(MainScene.Layer.bullet, this);
        }
    }

    @Override
    public RectF getCollisionRect() {
        return dstRect;
    }

    @Override
    public void onRecycle() {

    }

    public int getPower() {
        return power;
    }
}
