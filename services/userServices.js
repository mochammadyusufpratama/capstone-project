// Import the functions you need from the SDKs you need
const { initializeApp } = require("firebase/app");
const { getAnalytics } = require("firebase/analytics");
const { Datastore } = require('@google-cloud/datastore')
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyDcOExl3EL6Z3V0JzVh-L3LP2eaarjEmG8",
    authDomain: "capstoneproject-407212.firebaseapp.com",
    projectId: "capstoneproject-407212",
    storageBucket: "capstoneproject-407212.appspot.com",
    messagingSenderId: "603037324334",
    appId: "1:603037324334:web:691968450c6438e7df3bbf",
    measurementId: "G-TR08N02GXY"
};

// Initialize Firebase

const app = initializeApp(firebaseConfig)

const projectId = app.options.projectId
const keyFilename = 'credentials.json'
// Instantiate a Datastore client with the project ID
const db = new Datastore({
    projectId,
    keyFilename,
})

const pool = {
    db
}

const addUser = async (registerUser, pool) => {
    try {
        const entity = {
            key: pool.db.key(['users']),
            data: registerUser,
        }

        try {
            await pool.db.save(entity)
            return entity.data
        } catch (err) {
            throw new Error(err.message)
        }
    } catch (error) {
        // eslint-disable-next-line no-console
        console.log(error.message)
        return error
    }
}

module.exports = { pool, addUser }