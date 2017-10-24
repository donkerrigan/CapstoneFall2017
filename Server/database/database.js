var database = require('mongoose')
database.Promise = require('bluebird')
console.log('Initializing database connection...')

try {
  database.connect(process.env.MONGODB_URI)
  console.log('Connection to database successful')
} catch (err) {
  database.createConnection(process.env.MONGODB_URI)
}

module.exports = database
