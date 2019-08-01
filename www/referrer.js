var cordova = require('cordova');
var referrer = {
    getReferrer : function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'Referrer', 'getReferrer', []);
    },

    getInstallSource: function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'Referrer', 'getInstallSource', []);
    },

    isInstalledByPlayStore: function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'Referrer', 'isInstalledByPlayStore', []);
    }
};

module.exports = referrer;