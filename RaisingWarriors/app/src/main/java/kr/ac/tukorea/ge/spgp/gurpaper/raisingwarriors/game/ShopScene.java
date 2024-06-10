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

public class ShopScene extends Scene {
    private final Money money;
    private final JoyStick joyStick;
    private Warrior warrior;
    private static int DAMAGE_GOLD=10, ATTACK_SPEED_GOLD=10, HP_GOLD=10, SPEED_GOLD=10;
    MoneyImage moneyImage;
    DamageGoldImage damageGoldImage;
    AttackSpeedGoldImage attackSpeedGoldImage;
    HpGoldImage hpGoldImage;
    SpeedGoldImage speedGoldImage;
    public enum Layer {
        bg, touch , ui, COUNT
    }

    public ShopScene() {
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
                if(money.money < 10)
                    return false;
                Sound.playEffect(R.raw.btn_click_sound);
                warrior.POWER += 1;
                money.money -= DAMAGE_GOLD;
                DAMAGE_GOLD += 2;
                damageGoldImage.setMoney(DAMAGE_GOLD);
                moneyImage.setMoney(money.money);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.attack_speed, 1.0f, 6.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                Sound.playEffect(R.raw.btn_click_sound);
                warrior.FIRE_INTERVAL -= 0.01f;
                money.money -= ATTACK_SPEED_GOLD;
                ATTACK_SPEED_GOLD += 2;
                attackSpeedGoldImage.setMoney(ATTACK_SPEED_GOLD);
                moneyImage.setMoney(money.money);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.speed_up, 1.0f, 9.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                Sound.playEffect(R.raw.btn_click_sound);
                warrior.SPEED += 1;
                money.money -= SPEED_GOLD;
                SPEED_GOLD += 2;
                speedGoldImage.setMoney(SPEED_GOLD);
                moneyImage.setMoney(money.money);
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.health_up, 1.0f, 12.0f, 2.0f, 2.00f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if(money.money < 10)
                    return false;
                Sound.playEffect(R.raw.btn_click_sound);
                warrior.MAX_HP += 5;
                money.money -= HP_GOLD;
                HP_GOLD += 2;
                hpGoldImage.setMoney(HP_GOLD);
                moneyImage.setMoney(money.money);
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
        this.damageGoldImage= new DamageGoldImage(R.mipmap.number_24x32, Metrics.width/2 +1, 2.5f, 0.5f);
        damageGoldImage.setMoney(DAMAGE_GOLD);
        add(Layer.ui, damageGoldImage);
        this.attackSpeedGoldImage = new AttackSpeedGoldImage(R.mipmap.number_24x32, Metrics.width/2 + 1, 5.5f, 0.5f);
        attackSpeedGoldImage.setMoney(ATTACK_SPEED_GOLD);
        add(Layer.ui, attackSpeedGoldImage);
        this.hpGoldImage = new HpGoldImage(R.mipmap.number_24x32, Metrics.width/2 + 1, 11.5f, 0.5f);
        hpGoldImage.setMoney(HP_GOLD);
        add(Layer.ui, hpGoldImage);
        this.speedGoldImage = new SpeedGoldImage(R.mipmap.number_24x32, Metrics.width/2 +1, 8.5f, 0.5f);
        speedGoldImage.setMoney(SPEED_GOLD);
        add(Layer.ui, speedGoldImage);
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
