package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.MainPage;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.MovingBackground;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.Sound;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Score;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.app.MainActivity;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Warrior warrior;
    private final JoyStick joyStick;
    private Money money;
    public static String KEY_STAGE = "stage";
    public static final String KEY_STAGE_ID = "stageId";
    private int stage;
    Score score; // package private
    IngameHpImage ingameHpImage;
    public int deadEnemy = 0;

    private static  MainActivity ma;

    public int getScore() {
        return score.getScore();
    }

    public enum Layer {
        bg, enemy, bullet, player, ui, touch,controller, COUNT
    }
    public MainScene() {
        money = Money.getInstance();
        Intent intent = GameActivity.activity.getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            stage = extras.getInt(KEY_STAGE);
        }
        initLayers(Layer.COUNT);

        this.joyStick = new JoyStick(R.mipmap.joystick_bg, R.mipmap.joystick_thumb);
        joyStick.setRects(1, 15, 1, 0.3f, 0.8f);
        this.warrior = Warrior.getInstance(joyStick);
        this.warrior.initWarrior(joyStick);
        warrior.hp = warrior.MAX_HP;
        add(Layer.player, warrior);


        if(stage==1)
            add(Layer.bg, new MovingBackground(R.mipmap.map_1, this.warrior));
        else if (stage == 2)
            add(Layer.bg, new MovingBackground(R.mipmap.map_2, this.warrior));
        else if (stage == 3)
            add(Layer.bg, new MovingBackground(R.mipmap.map_3, this.warrior));

        add(Layer.touch, new Button(R.mipmap.btn_pause, 8.5f, 0.5f, 1.0f, 1.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                new PausedScene().push();
                return false;
            }
        }));
        this.score = new Score(R.mipmap.number_24x32, Metrics.width - 0.5f, 0.5f, 0.6f);
        score.setScore(0);
        add(Layer.controller, joyStick);

        add(Layer.controller, new EnemyGenerator(stage));
        add(Layer.controller, new CollisionChecker(this));
        this.ingameHpImage = new IngameHpImage(R.mipmap.number_24x32, Metrics.width/2, 0.7f, 0.5f);
        ingameHpImage.setHp(warrior.hp);
        add(Layer.ui, ingameHpImage);
        //add(Layer.ui, score);
    }
    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
    @Override
    public boolean onTouch(MotionEvent event) {
        super.onTouch(event);
        return joyStick.onTouch(event);
    }
    public void addScore(int amount) {
        score.add(amount);
    }
    protected void onStart() {
        Sound.playMusic(R.raw.main);
    }
    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        if(deadEnemy == 10){
            deadEnemy = 0;
            money.money += stage*100;
            finishActivity();
        }
        if(warrior.hp <= 0){
            finishActivity();
        }
        ingameHpImage.setHp(warrior.hp);
    }
}
