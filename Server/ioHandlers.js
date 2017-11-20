var commandHandlers = require('./commandHandlers.js')
var Promise = require('bluebird')

var message = function (data) {
	console.log(data);
};

var login = function (data) {
	var that = this;
	var theData = JSON.parse(data);
	commandHandlers.login(theData).then(function (loginSuccess){
		console.log(loginSuccess.username, 'successfully logged in.');
		that.emit('login', loginSuccess.toString());
	}).catch(function (){
		console.log('Login attempt failed.');
		that.emit('login', null);
	});
};

var signup = function (data) {
	var theData = JSON.parse(data);
	var that = this;
	commandHandlers.signup(theData).then(function (userObjectFound) {
		console.log(userObjectFound.username, 'account successfully created.');
		that.emit('signup', userObjectFound);
	}).catch(function (userObjectFound){
		that.emit('signup', userObjectFound);
	});
};

module.exports = {login, signup, message};