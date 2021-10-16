package com.magiclockers.contacts;
import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;

import com.airbnk.sdk.MainApi;
import com.airbnk.sdk.callback.IConnectDeviceCallback;
import com.airbnk.sdk.callback.IDeviceStatusCallback;
import com.airbnk.sdk.callback.IUnlockCallback;

public class Api extends Activity {
  private MainApi mainApi;
  public String value = "init";
  String key = "airbnkHaha123456";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle extras = getIntent().getExtras();
    //if (extras != null) {
    final String sn = extras.getString("sn");
    //}
    try {
      mainApi = new MainApi(Api.this, sn, key);
      if (mainApi != null) {
        System.out.print(mainApi);
        mainApi.connect(new IConnectDeviceCallback() {
          @Override
          public void onSuccess() {
            value = "connect succeed";
            mainApi.unlock(sn, new IUnlockCallback() {
              @Override
              public void onSuccess() {
                value = "unlock succeed";
                if (mainApi != null) {
                  mainApi.disconnect();
                }
              }

              @Override
              public void onFailed(String errorMsg) {
                value = errorMsg;
                if (mainApi != null) {
                  mainApi.disconnect();
                }
              }
            });
          }

          @Override
          public void onFailed(String errorMsg) {
            value = errorMsg;
            if (mainApi != null) {
              mainApi.disconnect();
            }
          }
        }, new IDeviceStatusCallback() {
          @Override
          public void states(int i) {
            value = "connect failed";
            if (mainApi != null) {
              mainApi.disconnect();
            }
          }
        });
      }
      ///
      new android.os.Handler(Looper.getMainLooper()).postDelayed(
        new Runnable() {
          public void run() {
            Api.this.finish();
            if (mainApi != null) {
              mainApi.disconnect();
            }
          }
        },
        7000);//force close activity and connection after a while
      ///

    } catch (Exception e) {
      Api.this.finish();
      if (mainApi != null) {
        mainApi.disconnect();
      }
      e.getCause();
      e.printStackTrace();
    }

    //connect end
  }

}
