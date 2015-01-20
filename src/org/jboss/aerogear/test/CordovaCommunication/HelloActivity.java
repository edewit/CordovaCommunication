package org.jboss.aerogear.test.CordovaCommunication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.plugin.Hello;
import org.apache.cordova.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloActivity extends Activity implements CordovaInterface {
  public static String TAG = "HelloActivity";
  private CordovaPlugin activityResultCallback;
  private boolean activityResultKeepRunning;
  protected boolean keepRunning = true;

  private final ExecutorService threadPool = Executors.newCachedThreadPool();
  private CordovaWebView cordovaWebView;

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    cordovaWebView = (CordovaWebView) findViewById(R.id.tutorialView);
    Config.init(this);
    cordovaWebView.loadUrl(Config.getStartUrl());
  }

  public void callJs(View view) throws JSONException {
    final JSONObject json = new JSONObject();
    json.put("message", "callTimer()");
    PluginResult result = new PluginResult(PluginResult.Status.OK, json);
    result.setKeepCallback(true);

    Hello.context.sendPluginResult(result);
  }
  /**
   * Called when an activity you launched exits, giving you the requestCode you started it with,
   * the resultCode it returned, and any additional data from it.
   *
   * @param requestCode The request code originally supplied to startActivityForResult(),
   *                    allowing you to identify who this result came from.
   * @param resultCode  The integer result code returned by the child activity through its setResult().
   * @param intent      An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    CordovaPlugin callback = this.activityResultCallback;
    if (callback != null) {
      callback.onActivityResult(requestCode, resultCode, intent);
    }
  }

  /**
   * Launch an activity for which you would like a result when it finished. When this activity exits,
   * your onActivityResult() method will be called.
   *
   * @param command     The command object
   * @param intent      The intent to start
   * @param requestCode The request code that is passed to callback to identify the activity
   */
  public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
    this.activityResultCallback = command;
    this.activityResultKeepRunning = this.keepRunning;

    // If multitasking turned on, then disable it for activities that return results
    if (command != null) {
      this.keepRunning = false;
    }

    // Start activity
    super.startActivityForResult(intent, requestCode);
  }

  @Override
  public void setActivityResultCallback(CordovaPlugin cordovaPlugin) {
    this.activityResultCallback = cordovaPlugin;
  }

  @Override
  public Activity getActivity() {
    return this;
  }

  @Override
  public Object onMessage(String id, Object data) {
    if (!"onScrollChanged".equals(id)) {
      LOG.d(TAG, "onMessage(" + id + "," + data + ")");
    }

    return null;
  }

  @Override
  public ExecutorService getThreadPool() {
    return threadPool;
  }
}
