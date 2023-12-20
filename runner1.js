// runner.js
const { spawn } = require('child_process')

// Path ke skrip Python dan argumen yang akan dikirimkan
const pythonScript = 'runner1.py'
const imagePath = 'https://storage.googleapis.com/ch2-ps507-bucket/upload/0.07356206916688302_O_1.jpg'

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
