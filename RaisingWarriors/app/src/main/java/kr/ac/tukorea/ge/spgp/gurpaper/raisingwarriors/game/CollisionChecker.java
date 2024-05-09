package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.util.CollisionHelper;

public class CollisionChecker implements IGameObject {
    private static final String TAG = CollisionChecker.class.getSimpleName();
    private final MainScene scene;

    private final AutoTarget autoTarget;

    public CollisionChecker(MainScene scene) {
        this.scene = scene;
        this.autoTarget = AutoTarget.getInstance();
    }

    @Override
    public void update(float elapsedSeconds) {
        ArrayList<IGameObject> enemies = scene.objectsAt(MainScene.Layer.enemy);
        for (int e = enemies.size() - 1; e >= 0; e--) {
            Enemy enemy = (Enemy)enemies.get(e);
            ArrayList<IGameObject> bullets = scene.objectsAt(MainScene.Layer.bullet);
            for (int b = bullets.size() - 1; b >= 0; b--) {
                Bullet bullet = (Bullet)bullets.get(b);
                if (CollisionHelper.collides(enemy, bullet)) {
                    //Log.d(TAG, "Collision !!");
                    scene.remove(MainScene.Layer.bullet, bullet);
                    boolean dead = enemy.decreaseLife(bullet.getPower());
                    if (dead) {
                        autoTarget.clear();
                        scene.remove(MainScene.Layer.enemy, enemy);
                        scene.addScore(enemy.getScore());
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
