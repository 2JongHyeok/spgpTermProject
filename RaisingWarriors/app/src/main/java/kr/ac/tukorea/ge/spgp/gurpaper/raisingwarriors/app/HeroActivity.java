package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.HeroScene;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.ShopScene;

public class HeroActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HeroScene().push();
    }
}
