package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.view.MotionEvent;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.MainPage;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Score;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private final Warrior warrior;
    private final JoyStick joyStick;
    Score score; // package private

    public int getScore() {
        return score.getScore();
    }

    public enum Layer {
        bg, enemy, bullet, player, ui, controller, COUNT
    }
    public MainScene() {
        //Metrics.setGameSize(16, 16);
        initLayers(Layer.COUNT);

        add(Layer.controller, new EnemyGenerator());
        add(Layer.controller, new CollisionChecker(this));

       add(Layer.bg, new MainPage(R.mipmap.map_1));

        this.joyStick = new JoyStick(R.mipmap.joystick_bg, R.mipmap.joystick_thumb);
        joyStick.setRects(1, 15, 1, 0.3f, 0.8f);
        this.warrior = new Warrior(joyStick);
        add(Layer.player, warrior);
        add(Layer.controller, joyStick);

        this.score = new Score(R.mipmap.number_24x32, Metrics.width - 0.5f, 0.5f, 0.6f);
        score.setScore(0);
        add(Layer.ui, score);
    }
    @Override
    public boolean onTouch(MotionEvent event) {
        return joyStick.onTouch(event);
    }
    public void addScore(int amount) {
        score.add(amount);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }
}
