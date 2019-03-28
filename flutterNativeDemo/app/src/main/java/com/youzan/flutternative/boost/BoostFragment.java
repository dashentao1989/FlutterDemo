package com.youzan.flutternative.boost;

import com.taobao.idlefish.flutterboost.containers.BoostFlutterFragment;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class BoostFragment extends BoostFlutterFragment {

    @Override
    public void destroyContainer() {

    }

    @Override
    public String getContainerName() {
        return "sample://secondPage";
    }

    @Override
    public Map getContainerParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        return params;
    }

    @Override
    public void onRegisterPlugins(PluginRegistry registry) {
//params of the page
        GeneratedPluginRegistrant.registerWith(registry);
    }
}
