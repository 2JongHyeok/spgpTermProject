package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;

public class Warrior extends Sprite {
    public Warrior() {
        super(R.mipmap.charactor);
        setPosition(Metrics.width / 2, Metrics.height / 2, Metrics.width, height);
    }
}
