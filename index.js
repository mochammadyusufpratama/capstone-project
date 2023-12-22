const express = require('express')
const Multer = require('multer')
const bodyParser = require('body-parser')
const app = express()
const port = 5000

// Module Import
const GcpBucket = require('./infrastructures/GcpBucket')
const { addImg, imgPool } = require('./services/imgServices')

const newsApi = require('./api/news')
const usersApi = require('./api/users')
const modelApi = require('./api/model/runner1')

app.use(express.json())
app.use(bodyParser.json())

app.use('/api', newsApi)
app.use('/api', usersApi)
app.use('/api', modelApi)

const multer = Multer({
    storage: Multer.memoryStorage(),
    limits: {
        fileSize: 5 * 1024 * 1024, // Limit file size to 5MB
    },
})

app.get('/', (req, res) => {
    res.send('Selamat Datang di Sampahku App!')
})

app.post('/upload', multer.single('photo'), async (req, res) => {

    try {

        const file = req.file
        const { id } = req.body
        console.log(file)

        if (!file) {
            return res.status(400).send('No file uploaded.')
        }

        const originalName = file.originalname

        const gcpBucket = new GcpBucket()

        console.log(GcpBucket)

        const imageUrl = await gcpBucket.uploadImgToBucket('upload', file)

        const newImgUrl = {
            id, imageUrl
        }

        const postedImgUrl = await addImg(newImgUrl, imgPool)

        console.log(postedImgUrl.imageUrl)

        res.redirect(`/api/predict?data=${postedImgUrl.imageUrl}`)

    } catch (error) {

        console.error('Error uploading file:', error)
        res.status(500).send('Error uploading file.')

    }

})

app.listen(port, () => {
    console.log(`Sampahku app listening on http://localhost:${port}`);
});
