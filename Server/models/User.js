var mongoose = require('mongoose')
var Schema = mongoose.Schema
var userSchema = new Schema({
	userID: {
		type: String,
		required: true
	},
	username: {
		type: String,
		required: true
	},
	password: {
		type: String,
		required: true
	}
})


module.exports = mongoose.model('User', userSchema)
