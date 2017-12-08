var mongoose = require('mongoose')
var Schema = mongoose.Schema
var highScoreSchema = new Schema({
	users: [{type: String}],
	scores: [{type: Number}]
})


module.exports = mongoose.model('HighScore', highScoreSchema)
