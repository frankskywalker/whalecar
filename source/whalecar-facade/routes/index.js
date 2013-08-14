/*
 * GET home page.
 */
var service = require("./tools/service-header");
var async = require("async");

// index page
exports.page = function(req, res, next) {
    res.render("index");
};

