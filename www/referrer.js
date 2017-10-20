var cordova = require('cordova');
var referrer = {
    getReferrer : function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'Referrer', 'getReferrer', []);
    }
};

module.exports = referrer;