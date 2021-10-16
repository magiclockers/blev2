package com.magiclockers.contacts;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.airbnk.sdk.MainApi;
import com.airbnk.sdk.callback.IConnectDeviceCallback;
import com.airbnk.sdk.callback.IDeviceStatusCallback;
import com.airbnk.sdk.callback.IUnlockCallback;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;

public class Api extends Activity {
  private MainApi mainApi;
  public String value = "init";
  String sn = "TiHeSBp9BlLKFW6jgfondDy9ovkoUGwbsFLES3HYkRPQ48KL5aZe9dMtLCS/I44M/wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//igVOBiVnz7W2LScEbB3TdUWjQH+gTO5sfaexY2pOf+39SoNpk07DhbbEfkoqemCgrwp++6XTAcupECUJbt9NgQ4qZlVxTNm7OgQ8TBSIt71KxQfNH0iMUHJ0KRMwBVXlysgpW7Tzt+sl3GoX3zF6n/BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR//8FWVldClKlHSU9aKvRUf//BVlZXQpSpR0lPWir0VH//wVZWV0KUqUdJT1oq9FR/+i/cVq1OcLiYGSr/pfPSVQ=a8677206db3ee35f1c4a1700eaa2c8d7";
  String key = "airbnkHaha123456";
  public SQLiteDatabase db;
  public final BleConnectStatusListener mConnectStatusListener = new BleConnectStatusListener() {
    @Override
    public void onConnectStatusChanged(String mac, int status) {
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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

    } catch (Exception e) {
      e.getCause();
      e.printStackTrace();
    }

    //connect end
  }

}
