package com.example.orientation19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.wangyuwei.particleview.ParticleView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ParticleView particleView = findViewById(R.id.particle_view);
        particleView.startAnim();
        particleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
