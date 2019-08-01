# cordova-plugin-referrer

cordova-plugin-referrer is a small cordova plugin to capture the INSTALL_REFERRER broadcast sent by Android during install of your app. The referrer is saved to the app preferences and can be retrieved at any time while your app runs.

The install referrer is the *referrer* parameter of the Play Store URL:

```https://play.google.com/store/apps/details?id=com.my.app?referrer=thisisthereferrer```

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

The plugin returns a string in any case. The onSuccess callback returns the referrer if one was detected, or an empty string if no referrer was found. The onError returns an error message as string.
