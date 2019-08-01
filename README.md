# cordova-plugin-referrer

cordova-plugin-referrer is a small cordova plugin to capture the INSTALL_REFERRER broadcast sent by Android during install of your app. The referrer is saved to the app preferences and can be retrieved at any time while your app runs.

The install referrer is the *referrer* parameter of the Play Store URL:

https://</disablehyperlink>play.google.com/store/apps/details?id=com.my.app?*referrer*=**this_is_the_referrer**

## Installation

```cordova plugin add https://github.com/Kevga/cordova-plugin-referrer```

## Usage

From your Cordova app, retrieve the referrer with the following function after the *deviceready* event:

```
referrer.getReferrer(function onSuccess(referrer) {
    if (referrer.length()) {
        console.log("The referrer is: "+referrer);
    } else {
        console.log("No referrer found.");
    }
}, function onError(error) {
    console.log(error);
});
```
The function returns a string in any case. The onSuccess callback returns the referrer if one was detected, or an empty string if no referrer was found. The onError returns an error message as string.

Additional function to get the package name of the application that installed your app:
```
referrer.getInstallSource(function onSuccess(source) {
    if (source.length()) {
        console.log("The install source is: "+source);
    } else {
        console.log("No install source found. Application may have been installed from a downloaded apk.");
    }
}, function onError(error) {
    console.log(error);
});
```

Function to quickly check if the install source was the Google Play Store:
```
referrer.isInstalledByPlayStore(function onSuccess(result) {
    if (result == "true") {
        console.log("App was installed by the Play Store.");
    } else {
        console.log("App was not installed by the Play Store.");
    }
}, function onError(error) {
    console.log(error);
});
```

Please note that that the return values are "true" or "false", both as string. I could not figure out how to return an actual boolean through Cordova.