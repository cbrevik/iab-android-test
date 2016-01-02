"use strict";

const InAppBillingBridge = require("react-native").NativeModules.InAppBillingBridge;

class InAppBilling {
    static getPurchaseListingDetails(productId, callback) {
      InAppBillingBridge.getPurchaseListingDetails(productId, callback);
    }
}

module.exports = InAppBilling;
