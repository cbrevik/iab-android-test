"use strict";

const InAppBillingBridge = require("react-native").NativeModules.InAppBillingBridge;

class InAppBilling {
    static getPurchaseListingDetails(productId) {
      return InAppBillingBridge.getProductDetails(productId);
    }

    static purchase(productId) {
      return InAppBillingBridge.purchase(productId);
    }
}

module.exports = InAppBilling;
