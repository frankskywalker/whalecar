var restify = require("restify");

// create json restful service client
exports.client = restify.createJsonClient({
	url : 'http://localhost:8080',
    //url : 'http://121.199.52.168:8080',
    //url : 'http://192.168.1.107:8080',
    version : '*'
});