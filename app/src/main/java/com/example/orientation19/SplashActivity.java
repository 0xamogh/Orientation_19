package com.example.orientation19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orientation19.Database.MyDatabase;

import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends AppCompatActivity {
    private MyDatabase myDatabase = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ParticleView particleView = findViewById(R.id.particle_view);
        particleView.startAnim();
        particleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent;
                if (myDatabase.getDifferentItemsCount() > 0) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
    }
}
