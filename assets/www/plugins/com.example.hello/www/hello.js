/*global cordova*/
cordova.define("com.example.hello.hello",

    function (require, exports, module) {
        var exec = require("cordova/exec");

        var Hello = function(){};

        Hello.prototype.greet = function (name, successCallback, errorCallback) {
            exec(successCallback, errorCallback, "Hello", "greet", [name]);
        };

        module.exports = new Hello();

    });
