package com.youzan.flutternative;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import io.flutter.facade.FlutterFragment;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

public class FlutterTestFragment extends FlutterFragment {
    private static final String NAME_CHANNEL = "samples.flutter.io/fragment";

    @NonNull
    public static FlutterTestFragment createFragment(String initialRoute) {
        final FlutterTestFragment fragment = new FlutterTestFragment();
        final Bundle args = new Bundle();
        args.putString(FlutterFragment.ARG_ROUTE, initialRoute);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view instanceof FlutterView) {
            flutterListenerNative((FlutterView) view);
        }
    }

    private void flutterListenerNative(FlutterView flutterView) {
        /**
         * flutter调用native
         */
        new MethodChannel(flutterView, NAME_CHANNEL).setMethodCallHandler((methodCall,
                                                                           result) -> {
            // 获得名称
            if (methodCall.method.equals("getName")) {
                String name = "张三";
                if (!TextUtils.isEmpty(name)) {
                    result.success("张三");
                } else {
                    result.error("UNAVAILABLE", "name is null", null);
                }
            } else if (methodCall.method.equals("getAge")) { // 获取年龄
                result.success("18");
            } else {
                result.notImplemented();
            }
        });

    }
}
