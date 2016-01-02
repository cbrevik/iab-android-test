package com.idehub.IabTest;

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

    public InAppBillingBridgePackage(String licenseKey, String merchantId) {
        _licenseKey = licenseKey;
        _merchantId = merchantId;
    }

    private String _licenseKey;
    private String _merchantId;

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new InAppBillingBridge(reactContext, _licenseKey, _merchantId));
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
