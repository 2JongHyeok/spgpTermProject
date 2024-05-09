package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
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

    private static float target_x,target_y;
    private Warrior warrior;
    private int power;

    private Bullet(float x, float y, int power,float t_x, float t_y) {
        super(R.mipmap.gonggal);
        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.power = power;
        target_x = t_x;
        target_y = t_y;
        warrior = Warrior.getInstance(null);
    }
    public static Bullet get(float x, float y, int power, float t_x, float t_y) {
        Bullet bullet = (Bullet) RecycleBin.get(Bullet.class);
        if (bullet != null) {
            bullet.setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
            bullet.power = power;
            target_x = t_x;
            target_y = t_y;
            return bullet;
        }
        return new Bullet(x, y, power, t_x, t_y);
    }

    @Override
    public void update(float elapsedSeconds) {
        float distanceX = x - target_x;
        float distanceY = y - target_y;

        // 전체 거리
        float distance = (float)Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // 단위 벡터 계산
        float unitX = distanceX / distance;
        float unitY = distanceY / distance;

        // 이동할 거리 계산
        float moveDistance = SPEED * elapsedSeconds;

        // 실제 이동
        dx = unitX * moveDistance;
        dy = unitY * moveDistance;
        x -= dx;
        y -= dy;
        dstRect.set(x-width/4,y- warrior.getY()-height/4+ Metrics.height/2,
                x+width/4,y-warrior.getY()+height/4+Metrics.height/2);
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
