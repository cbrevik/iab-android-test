"use strict";

const InAppBillingBridge = require("react-native").NativeModules.InAppBillingBridge;

class InAppBilling {
    static getPurchaseListingDetails(productId, callback) {
      return InAppBillingBridge.getPurchaseListingDetails(productId);
    }
}

module.exports = InAppBilling;
