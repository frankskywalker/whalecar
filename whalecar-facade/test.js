var soap = require('soap');
var url = 'http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl';
soap.createClient(url, function(err, client) {
	console.log(err);
	console.log(client);
	//client.testString('a');
});