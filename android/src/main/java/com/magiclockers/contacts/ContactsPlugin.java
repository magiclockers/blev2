package com.magiclockers.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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

import static androidx.core.content.ContextCompat.startActivity;

class Aapi extends Activity {
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
    Intent intent = new Intent(this.getContext(), new Api().getClass());
    getActivity().startActivity(intent);
    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }

  @PluginMethod()
  public void getContacts(PluginCall call) {
    value = call.getString("value");
    Aapi api = new Aapi();
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
