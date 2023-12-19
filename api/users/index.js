const express = require('express')
const router = express.Router()
const {
    getAuth,
    createUserWithEmailAndPassword,
    signInWithEmailAndPassword,
    signOut
} = require('firebase/auth')

router.post('/register', async (req, res) => {

    const { email, password } = req.body

    try {

        const newUser = {
            email,
            password
        }

        const auth = getAuth()

        createUserWithEmailAndPassword(
            auth,
            newUser.email,
            newUser.password
        )

        res.status(200).json({
            message: 'User created successfully',
            newUser
        })

    } catch (error) {

        res.status(500).json({
            error: error.message
        })

    }

})

router.post('/login', async (req, res) => {

    const { email, password } = req.body

    try {

        const loginUser = {
            email,
            password
        }

        const auth = getAuth()

        const signIn = await signInWithEmailAndPassword(
            auth,
            loginUser.email,
            loginUser.password
        )

        const credential = await signIn.user.getIdToken(true)
        const refreshToken = signIn.user.refreshToken

        res.status(200).json({
            message: 'User Login successfully',
            credential,
            refreshToken
        })

    } catch (error) {

        res.status(500).json({
            error: error.message
        })

    }

})

router.post('/logout', async (req, res) => {

    const auth = getAuth()
    signOut(auth)

    res.status(200).json({
        message: 'User Logout successfully',
    })

})

module.exports = router