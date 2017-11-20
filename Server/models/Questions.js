var mongoose = require('mongoose')
var Schema = mongoose.Schema
var questionSchema = new Schema({
  questionText: {
	  type: String,
	  required: true
  },
  answer: {
    type: Number,
    required: true
  },
  explanation: {
	  type: String,
	  required: true
  },
  options: [type: Schema.ObjectId, ref: 'QuestionOption']
})


module.exports = mongoose.model('Questions', questionSchema)
