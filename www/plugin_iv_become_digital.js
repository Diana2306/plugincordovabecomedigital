var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'plugin_iv_become_digital', 'coolMethod', [arg0]);
};

exports.init = function (arg0, success, error) {
    exec(success, error, "plugin_iv_become_digital", "init", [arg0]);
};

exports.setBecomeCallback = function (success, error) {
    exec(success, error, "plugin_iv_become_digital", "setBecomeCallback", []);
};
