/**
 * Created by Sriram on 4/30/14.
 */
chrome.app.runtime.onLaunched.addListener(function() {

    chrome.app.window.create('global.html', {
        'bounds': {
            'width': 500,
            'height': 500
        }
    });
});