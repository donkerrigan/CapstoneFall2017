var mongoose = require('mongoose')
var Schema = mongoose.Schema
var questionOptionSchema = new Schema({
  optionID: {
    type: Number,
    required: true
  },
  optionText: {
	  type: String,
	  required: true
  }
})


module.exports = mongoose.model('QuestionOption', questionOptionSchema)
