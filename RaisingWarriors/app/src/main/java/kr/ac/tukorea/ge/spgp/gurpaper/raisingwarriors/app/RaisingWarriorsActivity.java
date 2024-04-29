package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.app;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.MainScene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;

public class RaisingWarriorsActivity extends GameActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}
