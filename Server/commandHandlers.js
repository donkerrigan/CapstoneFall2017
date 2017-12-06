var User = require('./models/User.js')
var Quizzes = require('./models/Quizzes.js')
var Promise = require('bluebird')

var message = function (data) {
  console.log(data);
  return data;
};

var login = function (data) {
	return new Promise(function (resolve, reject) {
		User.findOne({username: data.username}, function (error, userfound) {
			if (error) {
			console.log(error)
			reject(null)
		}

		}).then(function (userfound) {
			if(!userfound){
				console.log('Username not found on database')
				reject(null);
			}
			if(userfound && userfound.password === data.password){
				console.log('username found and password matches')
				resolve(userfound)
			}
			else reject(null)
		})
	})
};

var signup = function (data) {
	return new Promise(function (resolve, reject) {
		if (data["password"].length === 0 || (data["username"].length === 0))
		reject(false)

		var user = new User()
		user.userID = data.userID
		user.password = data.password
		user.username = data.username
		user.score = 0
	
		User.findOne({username: user.username}, function (error, userfound){
			if (error) {
			console.log(error)
			reject(null)
			}
		}).then(function (userfound) {
			if(userfound){
				reject(null)
			}
			else {
				user.save(function (error) {
					console.log('Creating account for:', user)
					resolve(user)
				})
			}
		})
	})
};

var saveQuiz = function (data) {
	return new Promise(function(resolve, reject){
		var quiz = new Quizzes();
		quiz.longitude = data.longitude;
		quiz.latitude = data.latitude;
		Quizzes.findOne({$and: [{latitude: data.latitude}, {longitude: data.longitude}]}, function(error, quizFound){
			console.log("Saving Quiz....")
			if(error){
				console.log(error);
				reject(null);
			}
		}).then(function(quizFound) {
			if(quizFound){
				reject(null);
			}
			else{
				quiz.title = data.title;
				quiz.info = data.info;
				quiz.questions = data.questions;
				quiz.save(function(error){
					console.log('Saving Quiz', quiz.title);
					resolve(quiz);
				});
			}
		});
	});
};

var pingQuizzes = function (data) {
	return new Promise(function (resolve, reject) {
	
		Quizzes.find({$and: [{$and: [{latitude: {$lt: (data.latitude+0.005)}},{latitude: {$gt: (data.latitude-0.001)}}]}, {$and: [{longitude: {$lt: (data.longitude+0.005)}},{longitude: {$gt: (data.longitude-0.001)}}]}]}, function (error, quizzesFound){
			if (error) {
				console.log(error);
				reject(null);
			}
		}).then(function (quizzesFound) {
			console.log('Quizzes Found');
			resolve(quizzesFound);
		});
	})
};

var updateUser = function (data) {
	return new Promise(function (resolve, reject) {
		User.updateOne({username: data.username}, {score: data.score}, function (error, updateResult){
			if (error) {
				console.log(error)
				reject(null)
			}
			resolve(data);
		});
	});
};

module.exports = {login, signup, message, pingQuizzes, updateUser, saveQuiz }
