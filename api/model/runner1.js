// runner.js
const express = require('express')
const router = express.Router()
const path = require('path')
const { spawn } = require('child_process')

router.get('/predict', async (req, res) => {

    // Path ke skrip Python dan argumen yang akan dikirimkan
    const pythonScript = path.join(__dirname, 'runner1.py')
    const imagePath = path.join(__dirname, 'R_1.jpg')

    console.log(pythonScript)

    // Jalankan skrip Python menggunakan child_process.spawn
    const pythonProcess = spawn('python', [pythonScript, imagePath])

    // Tangani output dari skrip Python
    pythonProcess.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`);
    })

    pythonProcess.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`);
    })

    pythonProcess.on('close', (code) => {
        console.log(`child process exited with code ${code}`);
    })

})

module.exports = router 