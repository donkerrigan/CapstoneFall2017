var ioHandlers = require('./ioHandlers.js')
var commands = require('./commands.js')()
var database = require('./database/database.js')

var create = function (server) {
  var io = require('socket.io')(server)
  
  io.on('connection', function (client) {
	  
    commands.map((command) => {
      client.on(command, ioHandlers[command])
    })
    io.emit('broadcast', client.id + ' has connected')
  })
  
  return io
}

module.exports = create
