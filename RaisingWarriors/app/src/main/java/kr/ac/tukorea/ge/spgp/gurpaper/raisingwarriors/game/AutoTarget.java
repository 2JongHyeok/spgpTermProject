package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;

public class AutoTarget {
    private Enemy[] enemies = new Enemy[300];
    private int nearestEnemy = -1;
    private float nearestDistance=  10000;


    private static AutoTarget instance;


    private AutoTarget() {
        clear();
    }
    public static synchronized AutoTarget getInstance() {
        if (instance == null) {
            instance = new AutoTarget();
        }
        return instance;
    }

    public void push(Enemy enemy){
        enemies[enemy.num] = enemy;
    }

    public int getNearestEnemy(){
        return nearestEnemy;
    }

    public void Update(float distance, int num){
        if(distance < nearestDistance){
            nearestEnemy = num;
            nearestDistance = distance;
        }
    }

    public void clear(){
        nearestDistance = 10000;
        nearestEnemy = -1;
    }

    public float getTargetX(int index){
        return enemies[index].getX();
    }
    public float getTargetY(int index){
        return enemies[index].getY();
    }
}
