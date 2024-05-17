package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.app;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.BuildConfig;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.databinding.ActivityMainBinding;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game.MainScene;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());;
        setStage(1);
    }

    private void runGameActivity() {
        Intent intent = new Intent(this, RaisingWarriorsActivity.class);
        intent.putExtra(MainScene.KEY_STAGE, stage);
        startActivity(intent);
    }

    private void setStage(int index) {
        this.stage = index;
        binding.stageTextView.setText("Stage " + index);
        binding.prevStageButton.setEnabled(index > 1);
        binding.nextStageButton.setEnabled(index < 3);
    }

    private void ChangeMap() {
        try {
            AssetManager assets = getAssets();
            String fileName = "maps/map_" +stage+".png";
            InputStream is = assets.open(fileName);
            Bitmap bmp = BitmapFactory.decodeStream(is);
            binding.stageImageView.setImageBitmap(bmp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onBtnStartGame(View view) {
        runGameActivity();
    }

    public void onBtnPreviousStage(View view) {
        setStage(stage - 1);
        ChangeMap();
    }
    public void onBtnNextStage(View view) {
        setStage(stage + 1);
        ChangeMap();
    }
}