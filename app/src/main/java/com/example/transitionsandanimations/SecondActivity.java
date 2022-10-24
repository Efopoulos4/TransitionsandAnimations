package com.example.transitionsandanimations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    String type;
    Animation rotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        type = getIntent().getStringExtra("Key");
        if (type != null) {
            Log.d("paok", "SecondActivity: ");
            activateAnimation();
        }
    }


    private void activateAnimation() {

        switch (type) {
            case "Explode":
                Explode explodeTransition = new Explode();
                explodeTransition.setDuration(500);
                getWindow().setEnterTransition(explodeTransition);
                break;
            case "Fade":
                Fade fadeTransition = new Fade();
                fadeTransition.setDuration(1000);
                getWindow().setEnterTransition(fadeTransition);
                break;
        }
    }

    public void explodeAnimation2(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this);
        Intent i = new Intent(this, getClass());
        i.putExtra("Key", "Explode");
        startActivity(i, options.toBundle());

    }

    public void rotateAnimation2(View view) {
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        view.startAnimation(rotateAnimation);
    }

    public void fadeAnimation2(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this);
        Intent i = new Intent(this, getClass());
        i.putExtra("Key", "Fade");
        startActivity(i, options.toBundle());
    }

    public void swapViews2(View view) {
        final ImageView Button1 = findViewById(R.id.imageButton11);
        final ImageView Button2 = findViewById(R.id.imageButton22);
        final ImageView Button3 = findViewById(R.id.imageButton33);
        final ImageView Button4 = findViewById(R.id.imageButton44);
        Intent intent = new Intent(this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(Button4, "but4"),
                Pair.create(Button3, "but3"),
                Pair.create(Button2, "but2"),
                Pair.create(Button1, "but1")
        );
        startActivity(intent, options.toBundle());
    }
}