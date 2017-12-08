var User = require('./models/User.js')
var Quizzes = require('./models/Quizzes.js')
var HighScores = require('./models/HighScores.js')
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
		
		
		/*quiz.title = data.title;
		quiz.info = data.info;
		quiz.questions = data.questions;
		quiz.save(function(error){
			console.log('Saving Quiz', quiz.title);
			if(error){
				console.log('Error saving');
				reject(null);
			}
			resolve(quiz);
		});*/
				
				
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
					if(error){
						console.log('Error saving');
						reject(null);
					}
					resolve(quiz);
				});
			}
		});
	});
};

var pingQuizzes = function (data) {
	return new Promise(function (resolve, reject) {
	
		Quizzes.find({$and: [{$and: [{latitude: {$lt: (data.latitude+0.06)}},{latitude: {$gt: (data.latitude-0.06)}}]}, {$and: [{longitude: {$lt: (data.longitude+0.06)}},{longitude: {$gt: (data.longitude-0.06)}}]}]}, function (error, quizzesFound){
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
			HighScores.findOne({}, function(error, highScoresFound) {
				if(error){
					console.log(error);
					reject(null);
				}
			}).then(function (highScoresFound) {
				var tempScore = 0;
				var tempUser = "";
				var userExists = false;
				var userIndex = 15;
				for(i=14; i>=0; i--){
					console.log('error saving scores', data.score);
					if(data.username == highScoresFound.users[i])
					{
						console.log('user exists', i);
						userExists = true;
						userIndex = i;
						break;
					}
				}
				if(!userExists){
					var tempHighScores = highScoresFound;
					var highScoreIndex = 15;
					if(data.score<=highScoresFound.scores[14]){
						resolve(data);
						return;
					}
					else{
						for(i=13; i>=0; i--){
							if(data.score<=highScoresFound.scores[i]){
								highScoresFound.scores[i+1] = data.score;
								highScoresFound.users[i+1] = data.username;
								for (j=i+2; j<15; j++){
									highScoresFound.scores[j]=tempHighScores.scores[j-1];
									highScoresFound.users[j]=tempHighScores.users[j-1];
								}
								break;
							}
							else if(data.score>highScoresFound.scores[i]){
								highScoresFound.scores[i] = data.score;
								highScoresFound.users[i] = data.username;
								for (j=i+1; j<15; j++){
									highScoresFound.scores[j]=tempHighScores.scores[j-1];
									highScoresFound.users[j]=tempHighScores.users[j-1];
								}
								break;
							}
						}
						HighScores.updateOne({}, {users: highScoresFound.users, scores: highScoresFound.scores}, function(error, scoresFound) {
							console.log('Updating High Scores...');
							if(error){
								console.log('There is an error');
							}
						});
					}
				}
				else if(userExists && userIndex==0){
					highScoresFound.scores[userIndex] = data.score;
					HighScores.updateOne({}, {users: highScoresFound.users, scores: highScoresFound.scores}, function(error, scoresFound) {
						console.log('Updating High Scores...');
						if(error){
							console.log('There is an error');
						}
					});
				}
				else if(userExists){
					console.log('adjusting scores', userIndex);
					highScoresFound.scores[userIndex] = data.score;
					for(i=userIndex; i>0; i--){
						if(data.score>highScoresFound.scores[i-1]){
							console.log(highScoresFound.users)
							highScoresFound.scores[i] = highScoresFound.scores[i-1];
							highScoresFound.users[i] = highScoresFound.users[i-1];
							highScoresFound.scores[i-1] = data.score;
							highScoresFound.users[i-1] = data.username;
						}
					}
					HighScores.updateOne({}, {users: highScoresFound.users, scores: highScoresFound.scores}, function(error, scoresFound) {
						console.log('Updating High Scores...');
						if(error){
							console.log('There is an error');
						}
					});
				}
			});
			resolve(data);
		});
	});
};

var getHighScores = function(data) {
	return new Promise(function(resolve, reject){
		HighScores.findOne({}, function(error, highScoresFound) {
			if(error){
				console.log(error);
				reject(null);
			}
		}).then(function (highScoresFound) {
			if(!highScoresFound){
				console.log('No High Scores found');
				reject(null);
			}
			else if(highScoresFound){
				console.log('High Scores found');
				resolve(highScoresFound);
			}
			else{
				reject(null);
			}
		})
	});
};

module.exports = {login, signup, message, pingQuizzes, updateUser, saveQuiz, getHighScores }
