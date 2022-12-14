package com.example.transitionsandanimations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Action.TransitionType type;
    Animation rotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        type = null;

        if (Action.hasActivated)
            activateAnimation();
    }

    private void activateAnimation() {
        type = (Action.TransitionType) getIntent().getSerializableExtra(Action.KEY_TYPE);
        switch (type) {
            case explode:
                Explode explodeTransition = new Explode();
                explodeTransition.setDuration(500);
                getWindow().setEnterTransition(explodeTransition);
                break;
            case slide:
                Slide slideTransition = new Slide();
                slideTransition.setDuration(500);
                getWindow().setEnterTransition(slideTransition);
                break;
            case fade:
                Fade fadeTransition = new Fade();
                fadeTransition.setDuration(500);
                getWindow().setEnterTransition(fadeTransition);
                break;
        }
    }

    public void explodeAnimation(View view) {
        Action.hasActivated = true;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
        Intent i = new Intent(this, getClass());
        i.putExtra(Action.KEY_TYPE, Action.TransitionType.explode);
        startActivity(i, options.toBundle());
    }
    public void rotateAnimation(View view) {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        view.startAnimation(rotateAnimation);
    }
    public void fadeAnimation(View view) {
        Action.hasActivated = true;
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
        Intent i = new Intent(this, getClass());
        i.putExtra(Action.KEY_TYPE, Action.TransitionType.fade);
        startActivity(i, options.toBundle());
    }
    public void swapViews(View view) {
        final ImageView Button1 = findViewById(R.id.imageButton);
        final ImageView Button2 = findViewById(R.id.imageButton2);
        final ImageView Button3 = findViewById(R.id.imageButton3);
        final ImageView Button4 = findViewById(R.id.imageButton4);
        Intent intent = new Intent(this, SecondActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(Button4, "but4"),
                Pair.create(Button3, "but3"),
                Pair.create(Button2, "but2"),
                Pair.create(Button1, "but1")
                );
        startActivity(intent, options.toBundle());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}