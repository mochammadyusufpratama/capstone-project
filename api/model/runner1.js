// runner.js
const express = require('express')
const router = express.Router()
const path = require('path')
const { spawn } = require('child_process')

router.get('/predict', async (req, res) => {

    // Path ke skrip Python dan argumen yang akan dikirimkan
    const imgUrl = req.query.data
    console.log(imgUrl)

    const pythonScript = 'runner1.py'
    const imagePath = imgUrl

    let receivedData = ''; // Variabel untuk menyimpan data yang diterima

    // Jalankan skrip Python menggunakan child_process.spawn
    const pythonProcess = spawn('python', [pythonScript, imagePath])

    pythonProcess.stdout.on('data', (data) => {
        // Mengonversi data yang diterima menjadi string dan menambahkannya ke variabel
        receivedData += data.toString()
    })

    // Tangani output dari skrip Python
    pythonProcess.stdout.on('data', (data) => {
        console.log(`stdout: ${data}`)
    })

    pythonProcess.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`)
    })

    pythonProcess.on('close', (code) => {
        console.log(`child process exited with code ${code}`)

        console.log(receivedData)

        const matches = receivedData.match(/(Organic|Recycled)/g)

        let result

        if (matches && matches.length > 0) {
            result = matches[0]; // First match found (either Organic or Recycled)
            console.log('Extracted:', result);
        } else {
            console.log('No match found for Organic or Recycled.');
        }

        res.redirect(`/api/specNews?tipe=${result}`)

    })

})

module.exports = router 