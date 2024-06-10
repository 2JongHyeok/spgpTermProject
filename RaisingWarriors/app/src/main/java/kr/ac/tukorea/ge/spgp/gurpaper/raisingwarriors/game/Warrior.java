package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.Sound;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.RecycleBin;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;

public class Warrior extends Sprite implements IBoxCollidable {
    private static final float WARRIOR_WIDTH = 1.f;
    private static final float WARRIOR_HEIGHT = WARRIOR_WIDTH * 259/181;
    public   float SPEED = 3.0f;

    public int MAX_HP = 100;

    public int POWER = 5;
    public float FIRE_INTERVAL = 1.0f;
    public int  ATTACK_SPEED = 0;
    private static final float BULLET_OFFSET = 0.8f;
    private JoyStick joyStick;
    private static Warrior instance;
    private AutoTarget autoTarget;
    private float angle;
    private float fireCoolTime = FIRE_INTERVAL-(ATTACK_SPEED*(float)0.01);

    public int hp;

    private Warrior(JoyStick joyStick) {
        super(R.mipmap.charactor);
         initWarrior(joyStick);
    }

    public void initWarrior(JoyStick joyStick) {
        x = Metrics.width / 2;
        y = 2 * Metrics.height / 3;
        angle = -90;
        setPosition(x , Metrics.height/2  , WARRIOR_WIDTH,WARRIOR_HEIGHT);
        this.joyStick = joyStick;
        autoTarget = AutoTarget.getInstance();
    }

    public static synchronized Warrior getInstance(JoyStick joyStick) {
        if (instance == null) {
            instance = new Warrior(joyStick);
        }
        return instance;
    }
    public RectF getCollisionRect() {
        return dstRect;
    }
    public static synchronized Warrior getInstance() {
        return instance;
    }

    @Override
    public void update(float elapsedSeconds) {
        if (joyStick.power > 0) {
            float distance = SPEED * joyStick.power * elapsedSeconds;
            float dx = (float) (distance * Math.cos(joyStick.angle_radian));
            float dy = (float) (distance * Math.sin(joyStick.angle_radian));
            if((x+dx > WARRIOR_WIDTH/2) && (x + dx < Metrics.width-WARRIOR_WIDTH/2))
                x += dx;
            y += dy;
            dstRect.set(x - WARRIOR_WIDTH/2, Metrics.height/2 - WARRIOR_HEIGHT/2, x + WARRIOR_WIDTH/2, Metrics.height/2+WARRIOR_HEIGHT/2);
            angle = (float) Math.toDegrees(joyStick.angle_radian);
        }
        fireBullet(elapsedSeconds);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
    public float getX(){return x;}
    public float getY(){return y;}
    private void fireBullet(float elapsedSeconds) {
        MainScene scene = (MainScene) Scene.top();
        if (scene == null) return;
        fireCoolTime -= elapsedSeconds;
        if (fireCoolTime > 0) return;
        int nearestEnemy = autoTarget.getNearestEnemy();
        if(nearestEnemy == -1) return;
        fireCoolTime = FIRE_INTERVAL-(ATTACK_SPEED*(float)0.01);
        Sound.playEffect(R.raw.attack_sound);

        Bullet bullet = Bullet.get(x, y, POWER,
                autoTarget.getTargetX(nearestEnemy), autoTarget.getTargetY(nearestEnemy));
        autoTarget.clear();

        scene.add(MainScene.Layer.bullet, bullet);
    }
}


