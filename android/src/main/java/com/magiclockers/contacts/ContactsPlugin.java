package com.magiclockers.contacts;

import android.app.Activity;
import android.content.Intent;


import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import com.airbnk.sdk.callback.IGetDynamicPasswordCallback;
import com.airbnk.sdk.MainApi;

class ApiPass extends Activity {
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
  public void echo(PluginCall call) {// connect bluetooth and unlock
    value = call.getString("value");
    Intent intent = new Intent(this.getContext(), new Api().getClass());
    intent.putExtra("sn", sn);

    getActivity().startActivity(intent);
    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }

  @PluginMethod()
  public void getContacts(PluginCall call) {// get password
    value = call.getString("value");
    ApiPass api = new ApiPass();
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
