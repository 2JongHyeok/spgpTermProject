package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    public static final float GEN_INTERVAL = 2.0f;
    private final Random random = new Random();
    private float enemyTime = 0;

    private int level = 1;
    private int totlaEnemy = 0;
    @Override
    public void update(float elapsedSeconds) {
        enemyTime -= elapsedSeconds;
        if (enemyTime < 0) {
            generate();
            enemyTime = GEN_INTERVAL;
        }
    }

    private void generate() {
        Scene scene = Scene.top();
        if (scene == null) return;
        if(totlaEnemy > 300)
            return;
        totlaEnemy++;
        scene.add(MainScene.Layer.enemy, Enemy.get(level, totlaEnemy));

        }

    @Override
    public void draw(Canvas canvas) {

    }
}
