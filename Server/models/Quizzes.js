var mongoose = require('mongoose')
var Schema = mongoose.Schema
var quizSchema = new Schema({
  title: {
	  type: String,
	  required: true
  },
  info: {
	  type: String,
	  required: true
  },
  questions: [type: Schema.ObjectId, ref: 'Questions'],
  latitude: {
	  type: Number,
	  required: true
  }
  longitude: {
	  type: Number,
	  required: true
  }
})


module.exports = mongoose.model('Quiz', quizSchema)
