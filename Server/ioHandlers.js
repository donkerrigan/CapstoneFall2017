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

var saveQuiz = function (data) {
	var that = this;
	var theData = JSON.parse(data);
	console.log(theData);
	commandHandlers.saveQuiz(theData).then(function (saveResult){
		console.log(saveResult, 'Quiz Saved Successfully');
		that.emit('saveQuiz', saveResult);
	}).catch(function (){
		console.log('Quiz Save ERROR');
		that.emit('saveQuiz', null);
	});
};

var pingQuizzes = function (data) {
	var theData = JSON.parse(data);
	var that = this;
	commandHandlers.pingQuizzes(theData).then(function (quizzesFound) {
		console.log(quizzesFound, 'Quizzes found successfully');
		that.emit('pingQuizzes', quizzesFound);
	}).catch(function (quizzesFound){
		that.emit('pingQuizzes', quizzesFound);
	});
};

var updateUser = function (data) {
	var that = this;
	var theData = JSON.parse(data);
	commandHandlers.updateUser(theData).then(function(updateResult) {
		console.log('User Object Updated');
	}).catch(function (updateResult) {
		console.log('Error Updating User Object');
	});
};

var getHighScores = function(data) {
	var that = this;
	var theData = JSON.parse(data);
	commandHandlers.getHighScores(theData).then(function (loadResult) {
		console.log('Loaded High Scores Successfully');
		that.emit('getHighScores', loadResult);
	}).catch(function(loadResult) {
		console.log('Error Loading High Scores');
		that.emit('getHighScores', null);
	});
};

module.exports = {login, signup, message, pingQuizzes, updateUser, saveQuiz, getHighScores };