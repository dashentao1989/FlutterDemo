package com.youzan.flutternative;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;

import io.flutter.app.FlutterFragmentActivity;
import io.flutter.facade.Flutter;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.view.FlutterView;

public class FlutterViewActivity extends FlutterFragmentActivity {
    private static final String FLUTTER_VIEW_CHANNEL = "samples.flutter.io/flutter_view";
    private static final String NATIVE_VIEW_CHANNEL = "samples.flutter.io/native_view";
    private static EventChannel.EventSink mEventSink;
    private static FlutterView flutterView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        setContentView(R.layout.activity_flutter_view);
        flutterView = Flutter.createView(
                FlutterViewActivity.this,
                getLifecycle(),
                "route1"
        );

        FrameLayout frameLayout = findViewById(R.id.container_view);
        frameLayout.addView(flutterView);
        nativeListenerFlutter(flutterView);
        flutterListenerNative(flutterView);
        loop();
    }

    private void loop() {
        mHandler = new MyHandler(this);
        mHandler.sendEmptyMessageDelayed(1, 3000);
        mHandler.sendEmptyMessageDelayed(2, 5000);
    }

    private static class MyHandler extends Handler {
        WeakReference<Activity> mReference;

        MyHandler(Activity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mReference.get() != null) {
                if (msg.what == 1) {
                    if (mEventSink != null) {
                        mEventSink.success("native notice to flutter");
                    }
                } else if (msg.what == 2) {
                    nativeInvokeFlutter(flutterView);
                }
            }
        }
    }

    /**
     * native调用flutter的方法
     *
     * @param flutterView
     */
    private static void nativeInvokeFlutter(FlutterView flutterView) {
        new MethodChannel(flutterView, FLUTTER_VIEW_CHANNEL).invokeMethod("welcome", "朱", new
                MethodChannel.Result() {

                    @Override
                    public void success(@Nullable Object o) {
                        mEventSink.success("invoke flutter method<welcome>: content = " + o);
                    }

                    @Override
                    public void error(String s, @Nullable String s1, @Nullable Object o) {

                    }

                    @Override
                    public void notImplemented() {

                    }
                });
    }

    /**
     * native监听flutter
     *
     * @param flutterView
     */
    private void nativeListenerFlutter(FlutterView flutterView) {
        /**
         * flutter调用native
         */
        new MethodChannel(flutterView, FLUTTER_VIEW_CHANNEL).setMethodCallHandler((methodCall,
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

    /**
     * flutter监听native
     *
     * @param flutterView
     */
    private void flutterListenerNative(FlutterView flutterView) {
        new EventChannel(flutterView, NATIVE_VIEW_CHANNEL).setStreamHandler(new EventChannel
                .StreamHandler() {

            @Override
            public void onListen(Object o, EventChannel.EventSink eventSink) {
                mEventSink = eventSink;
            }

            @Override
            public void onCancel(Object o) {
                mEventSink = null;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventSink = null;
        flutterView = null;
    }
}
