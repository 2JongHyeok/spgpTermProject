package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.databinding.ActivityMainBinding;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.interfaces.IGameObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private int stage, cookieIndex;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setStage(1);
    }
    private void setStage(int stage) {
        this.stage = stage;
        String text = getString(R.string.title_stage_fmt, stage);
        binding.stageTextView.setText(text);
        binding.prevButton.setEnabled(stage > 1);
        binding.nextButton.setEnabled(stage < 3);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startActivity(new Intent(this, RaisingWarriorsActivity.class));
        }
        return super.onTouchEvent(event);
    }
}