var restify = require("restify");

// create json restful service client
exports.client = restify.createJsonClient({
	url : 'http://121.199.52.168:8080',
	version : '*'
});