package com.youzan.flutternative.boost;

import com.taobao.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class FirstBoostActivity extends BoostFlutterActivity {

    @Override
    public String getContainerName() {
//specify the page name register in FlutterBoost
        return "sample://firstPage";
    }

    @Override
    public Map getContainerParams() {
        //params of the page
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        return params;
    }

    @Override
    public void onRegisterPlugins(PluginRegistry registry) {
//register flutter plugins
        GeneratedPluginRegistrant.registerWith(registry);
    }
}
