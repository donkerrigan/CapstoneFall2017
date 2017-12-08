module.exports = function () {
  var commands = [];
  commands.push('message');
  commands.push('login');
  commands.push('signup');
  commands.push('pingQuizzes');
  commands.push('updateUser');
  commands.push('saveQuiz');
  commands.push('getHighScores');
  return commands;
}