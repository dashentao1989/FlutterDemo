package com.youzan.flutternative;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.taobao.idlefish.flutterboost.FlutterBoostPlugin;
import com.taobao.idlefish.flutterboost.interfaces.IPlatform;
import com.youzan.flutternative.boost.FirstBoostActivity;
import com.youzan.flutternative.boost.SecondBoostActivity;

import java.util.Map;

import io.flutter.view.FlutterMain;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlutterMain.startInitialization(getApplicationContext());
        FlutterBoostPlugin.init(new IPlatform() {
            @Override
            public Application getApplication() {
                return MyApplication.this;
            }

            /**
             * get the main activity, this activity should always at the bottom of task stack.
             */
            @Override
            public Activity getMainActivity() {
                if (MainActivity.sRef != null) {
                    return MainActivity.sRef.get();
                }

                return null;
            }

            @Override
            public boolean isDebug() {
                return false;
            }

            /**
             * start a new activity from flutter page, you may need a activity router.
             */
            @Override
            public boolean startActivity(Context context, String url, int requestCode) {
                // 打开一个boost页面
                if (url.equals("start_first_boost")) {
                    Intent intent = new Intent(getApplicationContext(), FirstBoostActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                } else if (url.equals("start_second_boost")) { // 打开第二个boost页面
                    Intent intent = new Intent(getApplicationContext(), SecondBoostActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                return false;
            }

            @Override
            public Map getSettings() {
                return null;
            }
        });
    }
}
