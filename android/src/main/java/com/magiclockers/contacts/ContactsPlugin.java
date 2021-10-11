package com.magiclockers.contacts;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.airbnk.sdk.callback.IGetDynamicPasswordCallback;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;

import com.airbnk.sdk.callback.IConnectDeviceCallback;
import com.airbnk.sdk.callback.IDeviceStatusCallback;
import com.airbnk.sdk.callback.IUnlockCallback;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.airbnk.sdk.MainApi;

import androidx.appcompat.app.AppCompatActivity;


class Api extends Activity {
  private SQLiteDatabase db;
  public final BleConnectStatusListener mConnectStatusListener = new BleConnectStatusListener() {
    @Override
    public void onConnectStatusChanged(String mac, int status) {
    }
  };
}

@NativePlugin(
  requestCodes = {ContactsPlugin.REQUEST_CONTACTS}
)
public class ContactsPlugin extends Plugin {
  protected static final int REQUEST_CONTACTS = 12345; // Unique request code
  private MainApi mainApi;
  public String value = "init";
  String sn = "TiHeSBp9BlLKFW6jgfondDy9ovkoUGwbsFLES3HYkRPQ48KL5aZe9dMtLCS/I44M/wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//igVOBiVnz7W2LScEbB3TdUWjQH+gTO5sfaexY2pOf+39SoNpk07DhbbEfkoqemCgrwp++6XTAcupECUJbt9NgQ4qZlVxTNm7OgQ8TBSIt71KxQfNH0iMUHJ0KRMwBVXlysgpW7Tzt+sl3GoX3zF6n/BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR/+i/cVq1OcLiYGSr/pfPSVQ=a8677206db3ee35f1c4a1700eaa2c8d7";
  String key = "airbnkHaha123456";

  @PluginMethod()
  public void echo(PluginCall call) {
    value = call.getString("value");
//connect
    try {
      Api api = new Api();
      mainApi = new MainApi(api, sn, key);
      if(mainApi != null) {
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
              }
            });
          }

          @Override
          public void onFailed(String errorMsg) {
            value = errorMsg;
          }
        }, new IDeviceStatusCallback() {
          @Override
          public void states(int i) {
            value = "connect failed";
          }
        });
      }

    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    }

    //connect end
    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }

  @PluginMethod()
  public void getContacts(PluginCall call) {
    value = call.getString("value");
    Api api = new Api();
    mainApi = new MainApi(api, sn, key);
    mainApi.getDynamicPassword(sn, new IGetDynamicPasswordCallback() {
      @Override
      public void onSuccess(String password) {
        value = password;
      }

      @Override
      public void onFailed(String errorMsg) {
        value = errorMsg;
      }
    });

    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }
}
