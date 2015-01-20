/*global cordova*/
cordova.define("org.jboss.aerogear.cordova.hybrid",

    function (require, exports, module) {
        var exec = require("cordova/exec");

        var Interface = function(){};

        Interface.prototype.init = function (successCallback, errorCallback) {
            exec(successCallback, errorCallback, "JSInterface", "init", []);
        };

        module.exports = new Interface();

    });
