var restify = require("restify");

// create json restful service client
exports.client = restify.createJsonClient({
<<<<<<< Updated upstream
	url : 'http://localhost:8080',
    //url : 'http://121.199.52.168:8080',
=======
	//url : 'http://localhost:8080',
    url : 'http://121.199.52.168:8080',
>>>>>>> Stashed changes
    //url : 'http://192.168.0.113:8080',
    version : '*'
});