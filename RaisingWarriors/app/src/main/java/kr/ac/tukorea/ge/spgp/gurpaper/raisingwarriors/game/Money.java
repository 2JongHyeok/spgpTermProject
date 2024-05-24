package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;

public class Money {
    public int money;
    private static Money instance;
    private Money() {
        money = 0;
    }
    public static synchronized Money getInstance(){
        if (instance == null) {
            instance = new Money();
        }
        return instance;
    }
}
