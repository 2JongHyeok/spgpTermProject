package kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.game;

import android.app.AlertDialog;
import android.content.DialogInterface;

import kr.ac.tukorea.ge.spgp.gurpaper.raisingwarriors.R;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Button;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp.gurpaper.framework.view.Metrics;

public class PausedScene extends Scene {
    public enum Layer {
        bg, title, touch, COUNT
    }
    private float angle;
    public PausedScene() {
        initLayers(Layer.COUNT);
        float w = Metrics.width, h = Metrics.height;
        float cx = w / 2, cy = h / 2;
        add(Layer.bg, new Sprite(R.mipmap.trans_50b, cx, cy, w, h));
        add(Layer.bg, new Sprite(R.mipmap.pause_image, cx, cy, 12.00f, 6.75f));
        add(Layer.touch, new Button(R.mipmap.btn_resume_n, 14.5f, 1.0f, 2.0f, 0.75f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                pop();
                return false;
            }
        }));
        add(Layer.touch, new Button(R.mipmap.btn_exit_n, 8f, 5.5f, 2.667f, 1f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                new AlertDialog.Builder(GameActivity.activity)
                        .setTitle("Confirm")
                        .setMessage("Do you really want to exit the game?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finishActivity();
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        }));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        angle += (float) (elapsedSeconds * Math.PI / 4);
        float x = (float) (8.0f + 4.0f * Math.cos(angle));
        float y = (float) (4.5f + 2.0f * Math.sin(angle));
    }


    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }


    public boolean isTransparent() {
        return true;
    }
}
