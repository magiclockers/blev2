package com.magiclockers.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

//import androidx.appcompat.app.AppCompatActivity;
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

import org.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Api extends Activity {
  private final BleConnectStatusListener mConnectStatusListener = new BleConnectStatusListener() {
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
  private com.airbnk.sdk.MainApi mainApi;
  public String value = "init";

  @PluginMethod()
  public void echo(PluginCall call) {
    value = call.getString("value");
//test
    Api api = new Api();
    mainApi = new MainApi(api, "12345678901234567890123456789012", "bbbbbb");
  //  try {

    mainApi.connect(new IConnectDeviceCallback() {
      @Override
      public void onSuccess() {
        value = "connect succeed";
        mainApi.unlock("12345678901234567890123456789012", new IUnlockCallback() {
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
     ////m.invoke(testObject);
//    } catch (Exception e) {
//      e.getCause();
//        e.printStackTrace();
//    }

    //test
    JSObject ret = new JSObject();
    ret.put("value", value);
    call.success(ret);
  }
  @PluginMethod()
  public void getContacts(PluginCall call) {
    String value = call.getString("filter");
    // Filter based on the value if want
    JSObject ret = new JSObject();
    ret.put("firstName", "ayaya");
    ret.put("lastName", "ffff");
    ret.put("telephone", "ffff");
    call.success(ret);

   // saveCall(call);
   // pluginRequestPermission(Manifest.permission.READ_CONTACTS, REQUEST_CONTACTS);
  }

  @Override
  protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.handleRequestPermissionsResult(requestCode, permissions, grantResults);


    PluginCall savedCall = getSavedCall();
    if (savedCall == null) {
      Log.d("Test", "No stored plugin call for permissions request result");
      return;
    }

    for (int result : grantResults) {
      if (result == PackageManager.PERMISSION_DENIED) {
        Log.d("Test", "User denied permission");
        return;
      }
    }

    if (requestCode == REQUEST_CONTACTS) {
      // We got the permission!
      loadContacts(savedCall);
    }
  }

  void loadContacts(PluginCall call) {
    ArrayList<Map> contactList = new ArrayList<>();
    ContentResolver cr = this.getContext().getContentResolver();

    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
      null, null, null, null);
    if ((cur != null ? cur.getCount() : 0) > 0) {
      while (cur != null && cur.moveToNext()) {
        Map<String, String> map = new HashMap<String, String>();

        String id = cur.getString(
          cur.getColumnIndex(ContactsContract.Contacts._ID));
        String name = cur.getString(cur.getColumnIndex(
          ContactsContract.Contacts.DISPLAY_NAME));
        map.put("firstName", name);
        map.put("lastName", "");

        String contactNumber = "";

        if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
          Cursor pCur = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            new String[]{id}, null);
          pCur.moveToFirst();
          contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
          Log.i("phoneNUmber", "The phone number is " + contactNumber);
        }
        map.put("telephone", contactNumber);
        contactList.add(map);
      }
    }
    if (cur != null) {
      cur.close();
    }

    JSONArray jsonArray = new JSONArray(contactList);
    JSObject ret = new JSObject();
    ret.put("results", jsonArray);
    call.success(ret);
  }
}
