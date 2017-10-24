var server = require('http').createServer()
var io = require('./server/ioserver.js')(server)
var port = process.env.PORT || 3000


server.listen(port)

console.log('Server is up and listening on port:', port)
