
var restify = require("restify");

//create json restful service client
exports.client = restify.createJsonClient({
  url: 'http://localhost:8080',
  version: '*'
});