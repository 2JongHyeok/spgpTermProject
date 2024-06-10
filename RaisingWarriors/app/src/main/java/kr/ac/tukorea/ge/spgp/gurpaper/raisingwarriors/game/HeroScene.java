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
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.Sound;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class HeroScene extends Scene {
    private final Money money;
    private final JoyStick joyStick;
    private Warrior warrior;

    MoneyImage moneyImage;
    HeroAttackImage damageImage;
    HeroSpeedImage speedImage;
    HeroAttackSpeedImage attackSpeedImage;
    HeroHpImage hpImage;

    public enum Layer {
        bg, touch , ui, COUNT
    }

    public HeroScene() {
        Sound.playEffect(R.raw.click_sound);
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
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.attack_speed, 1.0f, 6.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {

                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.speed_up, 1.0f, 9.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {

                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.health_up, 1.0f, 12.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {

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
        this.moneyImage = new MoneyImage(R.mipmap.number_24x32, Metrics.width/2 - 1, 0.7f, 0.5f);
        moneyImage.setMoney(money.money);
        add(Layer.ui, moneyImage);
        this.damageImage = new HeroAttackImage(R.mipmap.number_24x32, Metrics.width/2 +1, 2.5f, 0.5f);
        damageImage.setDamage(warrior.POWER);
        add(ShopScene.Layer.ui, damageImage);
        this.attackSpeedImage = new HeroAttackSpeedImage(R.mipmap.number_24x32, Metrics.width/2 +1, 5.5f, 0.5f);
        attackSpeedImage.setAttackSpeed(warrior.ATTACK_SPEED);
        add(ShopScene.Layer.ui, attackSpeedImage);
        this.speedImage = new HeroSpeedImage(R.mipmap.number_24x32, Metrics.width/2 +1, 8.5f, 0.5f);
        speedImage.setSpeed((int)warrior.SPEED);
        add(ShopScene.Layer.ui, speedImage);
        this.hpImage = new HeroHpImage(R.mipmap.number_24x32, Metrics.width/2 +1, 11.5f, 0.5f);
        hpImage.setHp(warrior.MAX_HP);
        add(ShopScene.Layer.ui, hpImage);

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
