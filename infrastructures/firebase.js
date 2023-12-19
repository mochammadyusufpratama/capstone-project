const firebaseAdmin = require('firebase-admin')
const serviceAccount = require('../credentials.json')

firebaseAdmin.initializeApp({
    credential: firebaseAdmin.credential.cert(serviceAccount),
    databaseURL: 'https://capstoneproject-407212.firebaseapp.com'
})

module.exports = { firebaseAdmin }