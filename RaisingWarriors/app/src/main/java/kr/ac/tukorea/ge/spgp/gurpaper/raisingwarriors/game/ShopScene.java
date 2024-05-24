package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.MovingBackground;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Score;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.ShopBackGround;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class ShopScene extends Scene {
    private final Money money;
    private final JoyStick joyStick;
    private Warrior warrior;

    public enum Layer {
        bg, touch , text, COUNT
    }

    public ShopScene() {
        warrior.getInstance();
        initLayers(Layer.COUNT);
        add(Layer.bg, new ShopBackGround(R.mipmap.pause_image));
        money = Money.getInstance();
        initLayers(MainScene.Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;
        this.joyStick = new JoyStick(R.mipmap.joystick_bg, R.mipmap.joystick_thumb);
        joyStick.setRects(1, 15, 1, 0.3f, 0.8f);
        this.warrior = Warrior.getInstance(joyStick);
        this.warrior.initWarrior(joyStick);
        add(PausedScene.Layer.bg, new Sprite(R.mipmap.pause_image, cx, cy, 9.00f, 16.0f));
        add(Layer.touch, new Button(R.mipmap.damage_up, 1.0f, 3.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                warrior.POWER += 1;
                money.money -= 10;
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.attack_speed, 1.0f, 6.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                warrior.FIRE_INTERVAL -= 0.01f;
                money.money -= 10;
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.speed_up, 1.0f, 9.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                warrior.SPEED += 1;
                money.money -= 10;
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.health_up, 1.0f, 12.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                warrior.HP += 5;
                money.money -= 10;
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.money, 1.0f, 1.0f, 1.0f, 1.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {

                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.exit, 8.0f, 1.0f, 1.0f, 1.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                pop();
                finishActivity();
                return false;
            }
        }));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }
    protected int getTouchLayerIndex() {
        return ShopScene.Layer.touch.ordinal();
    }


    public boolean isTransparent() {
        return true;
    }
}
