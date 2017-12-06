var mongoose = require('mongoose')
var Schema = mongoose.Schema
var quizSchema = new Schema({
  info: {
	  type: String,
	  required: true
  },
  latitude: {
	  type: Number,
	  required: true
  },
  longitude: {
	  type: Number,
	  required: true
  },
  questions: [{type: Schema.Types.Mixed}],
  title: {
	  type: String,
	  required: true
  }
})


module.exports = mongoose.model('Quiz', quizSchema)
