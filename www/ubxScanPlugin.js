var exec = require('cordova/exec');

exports.doScan = function (success, error) {
    exec(success, error, 'ubxScanPlugin', 'doScan', []);
};
exports.onBarcodeScanned = function (success, error) {
    exec(success, error, 'ubxScanPlugin', 'onBarcodeScanned', []);
};
exports.isUBX = function (success, error) {
    exec(success, error, 'ubxScanPlugin', 'isUBX', []);
};
