package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.util.Gauge;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class Enemy extends AnimSprite implements IBoxCollidable, IRecyclable {
    private static final float SPEED = 3.0f;
    private static final float RADIUS = 1.0f;
    private static final int[] resIds = { R.mipmap.zombie_1,};
    public static final float ANIM_FPS = 5.0f;
    protected RectF collisionRect = new RectF();
    private int level;
    private int  life, maxLife;

    private Enemy(int level, int index) {
        super(0, 0);
        init(level, index);
        dy = SPEED;
    }

    private void init(int level, int index) {
        this.level = level;
        this.life = this.maxLife = (level + 1) * 10;
        setAnimationResource(resIds[0], ANIM_FPS, 9);  // [edit] 리소스 id 랜덤 값으로 변경하기
        setPosition(Metrics.width / 10 * (2 * index + 1), -RADIUS, RADIUS);
    }

    public static Enemy get(int level, int index) {
        Enemy enemy = (Enemy) RecycleBin.get(Enemy.class);
        if (enemy != null) {
            enemy.init(level, index);
            return enemy;
        }
        return new Enemy(level, index);
    }
    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if (dstRect.top > Metrics.height) {
            Scene.top().remove(MainScene.Layer.enemy, this);
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();

        float width = dstRect.width() * 0.7f;
        canvas.translate(x - width / 2, dstRect.bottom);
        canvas.scale(width, width);
        canvas.restore();
    }

    @Override
    public RectF getCollisionRect() {
        return collisionRect;
    }

    @Override
    public void onRecycle() {

    }

    public int getScore() {
        return (level + 1) * 100;
    }

    public boolean decreaseLife(int power) {
        life -= power;
        return life <= 0;
    }
}
