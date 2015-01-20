package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

    public static CallbackContext context;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
        result.setKeepCallback(true);

        context = callbackContext;
//        if (action.equals("greet")) {
//
//            String name = data.getString(0);
//            String message = "Hello, " + name;
//            callbackContext.success(message);
//
//            return true;
//
//        } else {

            callbackContext.sendPluginResult(result);

            return true;

//        }
    }
}
