var restful = require('node-restful');
var mongoose = restful.mongoose;

var statusSchema = new mongoose.Schema({
	fbname: String,
	content: String,
	likes: Number,
	comments: Number
});


module.exports = restful.model('tblstatus', statusSchema);
