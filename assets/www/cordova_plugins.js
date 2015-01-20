cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.example.hello/www/hello.js",
        "id": "com.example.hello.hello",
        "clobbers": [
            "hello"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.example.hello": "0.6.0"
}
// BOTTOM OF METADATA
});