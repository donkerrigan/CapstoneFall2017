var User = require('./models/User.js')
var Promise = require('bluebird')

var message = function (data) {
  console.log(data)
  return data
}

var login = function (data) {
	
}

var signup = function (data) {
	
}

module.exports = {login, signup, message}
