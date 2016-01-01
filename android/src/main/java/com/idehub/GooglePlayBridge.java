package com.idehub.IabTest;

import android.content.Context;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.android.vending.billing.IInAppBillingService;

public class GooglePlayBridge extends ReactContextBaseJavaModule {

    final String ANDROID_VENDING = "com.android.vending";
    final String SERVICE_INTENT = "com.android.vending.billing.InAppBillingService.BIND";
    ReactApplicationContext _reactContext;
    IInAppBillingService mService;

    public GooglePlayBridge(ReactApplicationContext reactContext) {
        super(reactContext);
        _reactContext = reactContext;
    }

    @Override
    public String getName() {
       return "GooglePlayBridge";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    ServiceConnection mServiceConn = new ServiceConnection() {
       @Override
       public void onServiceDisconnected(ComponentName name) {
           mService = null;
       }

       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           mService = IInAppBillingService.Stub.asInterface(service);
       }
    };

    public void openServiceConnection() {
      Intent serviceIntent = new Intent(SERVICE_INTENT);
      serviceIntent.setPackage(ANDROID_VENDING);
      _reactContext.bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    public void closeServiceConnection() {
      if (mService != null) {
        _reactContext.unbindService(mServiceConn);
      }
    }
}
