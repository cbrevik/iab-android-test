package com.idehub.IabTest;

import com.facebook.react.bridge.Callback;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

public class BillingHandler implements BillingProcessor.IBillingHandler {

    IBillingInitialized mOnBillingInitializedCallback;
    IProductPurchased mOnProductPurchasedCallback;
    IBillingError mOnBillingErrorCallback;
    IPurchaseHistoryRestored mOnPurchaseHistoryRestoredCallback;

    public BillingHandler(IBillingInitialized onBillingInitializedCallback,
                          IProductPurchased onProductPurchasedCallback,
                          IBillingError onBillingErrorCallback,
                          IPurchaseHistoryRestored onPurchaseHistoryRestoredCallback) {
        mOnBillingInitializedCallback = onBillingInitializedCallback;
        mOnProductPurchasedCallback = onProductPurchasedCallback;
        mOnBillingErrorCallback = onBillingErrorCallback;
        mOnPurchaseHistoryRestoredCallback = onPurchaseHistoryRestoredCallback;
    }

    @Override
    public void onBillingInitialized() {
        if (mOnBillingInitializedCallback != null)
            mOnBillingInitializedCallback.invoke();
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        if (mOnProductPurchasedCallback != null)
            mOnProductPurchasedCallback.invoke(productId, details);
    }
    @Override
    public void onBillingError(int errorCode, Throwable error) {
        if (mOnBillingErrorCallback != null)
            mOnBillingErrorCallback.invoke(errorCode, error);
    }

    @Override
    public void onPurchaseHistoryRestored() {
        if (mOnPurchaseHistoryRestoredCallback != null)
            mOnPurchaseHistoryRestoredCallback.invoke();
    }

    public interface IBillingInitialized {
        void invoke();
    }

    public interface IProductPurchased {
        void invoke(String productId, TransactionDetails details);
    }

    public interface IBillingError {
        void invoke(int errorCode, Throwable error);
    }

    public interface IPurchaseHistoryRestored {
        void invoke();
    }
}
