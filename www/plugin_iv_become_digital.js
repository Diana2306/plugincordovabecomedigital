var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'plugin_iv_become_digital', 'coolMethod', [arg0]);
};

exports.initBecome = function (arg0, success, error) {
    exec(success, error, "plugin_iv_become_digital", "initBecome", [arg0]);
};

exports.setBecomeCallback = function (success, error) {
    exec(success, error, "plugin_iv_become_digital", "setBecomeCallback", []);
};

exports.echo = function(arg0, success, error) {
    exec(success, error, 'plugin_iv_become_digital', 'echo', [arg0]);
  };
  
  exports.echojs = function(arg0, success, error) {
    if (arg0 && typeof(arg0) === 'string' && arg0.length > 0) {
      success(arg0);
    } else {
      error('Empty message!');
    }
  };
