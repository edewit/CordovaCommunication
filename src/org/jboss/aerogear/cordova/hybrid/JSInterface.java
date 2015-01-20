package org.jboss.aerogear.cordova.hybrid;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class JSInterface extends CordovaPlugin {

  public static CallbackContext context;

  @Override
  public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
    context = callbackContext;

    if (action.equals("init")) {
      PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
      result.setKeepCallback(true);

      callbackContext.sendPluginResult(result);

      return true;
    }
    return false;
  }

  public static void callJS(String function) {
    PluginResult result = new PluginResult(PluginResult.Status.OK, function);
    result.setKeepCallback(true);

    context.sendPluginResult(result);
  }
}
