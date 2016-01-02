package com.idehub.IabTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.util.HashMap;
import java.util.Map;

public class InAppBillingBridge extends ReactContextBaseJavaModule {
    ReactApplicationContext _reactContext;
    BillingProcessor _bp;
    String LICENSE_KEY = null;
    String MERCHANT_ID = null;

    public InAppBillingBridge(ReactApplicationContext reactContext, String licenseKey, String merchantId) {
        super(reactContext);
        _reactContext = reactContext;
        LICENSE_KEY = licenseKey;
        MERCHANT_ID = merchantId;
    }

    @Override
    public String getName() {
        return "InAppBillingBridge";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void getPurchaseListingDetails(String productId, Callback callback) {
        final Callback cb = callback;
        final BillingProcessor bp = _bp;
        final String id = productId;
        
        try {
          if (isIabServiceAvailable()) {
              BillingHandler handler = new BillingHandler(new BillingHandler.IBillingInitialized() {
                  @Override
                  public void invoke() {
                      SkuDetails details = bp.getPurchaseListingDetails(id);
                      cb.invoke(details.title);
                  }
              }, null, null, null);
              setupBillingProcessor(handler);
          }
        }
        finally {
            releaseBillingProcessor();
        }
    }

    private Boolean isIabServiceAvailable() {
        return BillingProcessor.isIabServiceAvailable(_reactContext);
    }

    private void setupBillingProcessor(BillingProcessor.IBillingHandler handler) {
        _bp = new BillingProcessor(_reactContext, LICENSE_KEY, MERCHANT_ID, handler);
    }

    private void releaseBillingProcessor() {
        if (_bp != null)
            _bp.release();
    }
}
