
/*
 * GET home page.
 */

exports.index = function(req, res){
  client.get('/foo/bar', function(err, req, res, obj) {
      assert.ifError(err);
      console.log('%j', obj);
  });
  res.render('index', { title: 'Express' });
};