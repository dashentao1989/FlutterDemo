package com.youzan.flutternative;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import io.flutter.app.FlutterFragmentActivity;
import io.flutter.facade.FlutterFragment;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class FlutterFgActivity extends FlutterFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        setContentView(R.layout.activity_flutter_fragment);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        FlutterFragment flutterFragment = FlutterTestFragment.createFragment("route2");
        tx.replace(R.id.container_fragment, flutterFragment);
        tx.commit();
    }

}
