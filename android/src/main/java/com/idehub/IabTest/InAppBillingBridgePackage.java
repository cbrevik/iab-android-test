package com.idehub.IabTest;

import android.app.Activity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.idehub.IabTest.InAppBillingBridge;

public class InAppBillingBridgePackage implements ReactPackage {

    public InAppBillingBridgePackage(String licenseKey, String merchantId, Activity activity) {
        _licenseKey = licenseKey;
        _merchantId = merchantId;
        _activity = activity;
    }

    public InAppBillingBridgePackage(Activity activity) {
        _activity = activity;
    }

    private String _licenseKey;
    private String _merchantId;
    private Activity _activity;

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
		if (_licenseKey == null)
        	modules.add(new InAppBillingBridge(reactContext, _activity));
		else
			modules.add(new InAppBillingBridge(reactContext, _licenseKey, _merchantId, _activity));
        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList();
    }
}
