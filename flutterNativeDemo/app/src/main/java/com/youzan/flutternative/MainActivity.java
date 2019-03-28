package com.youzan.flutternative;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.youzan.flutternative.boost.SecondBoostActivity;
import com.youzan.flutternative.boost.FirstBoostActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    public static WeakReference<MainActivity> sRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sRef = new WeakReference<>(this);
        setContentView(R.layout.activity_main);
        Button clickView = findViewById(R.id.click_view);
        Button clickFragment = findViewById(R.id.click_fragment);
        Button showBoostFg = findViewById(R.id.click_boost_fragment);
        Button showBoostAc = findViewById(R.id.click_boost_activity);
        showBoostAc.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, FirstBoostActivity.class);
            startActivity(intent);
        });

        showBoostFg.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SecondBoostActivity.class);
            startActivity(intent);
        });

        clickFragment.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, FlutterFgActivity.class);
            startActivity(intent);
        });

        clickView.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, FlutterViewActivity.class);
            startActivity(intent);
        });
    }
}
