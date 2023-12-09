const express = require('express');
const Multer = require('multer');
const app = express();
const port = 5000;

const GcpBucket = require('./infrastructures/GcpBucket');
const { addUser, pool } = require('./services/userServices');
const { addImg, imgPool } = require('./services/imgServices');

app.use(express.json());

const multer = Multer({
    storage: Multer.memoryStorage(),
    limits: {
        fileSize: 5 * 1024 * 1024, // Limit file size to 5MB
    },
});

app.get('/', (req, res) => {
    res.send('Hello World!');
});

app.post('/users', async (req, res) => {
    const { email, password } = req.body;

    const newUser = {
        email,
        password,
    };

    const postedUser = await addUser(newUser, pool);

    res.status(200).send({
        status: 'success',
        data: postedUser,
    });
});

app.post('/upload', multer.single('photo'), async (req, res) => {
    try {
        const file = req.file;
        const { id } = req.body;
        console.log(file);

        if (!file) {
            return res.status(400).send('No file uploaded.');
        }

        const originalName = file.originalname;

        const gcpBucket = new GcpBucket();

        console.log(GcpBucket)
        const imageUrl = await gcpBucket.uploadImagToBucket('upload', file);



        const newImgUrl = {
            id, imageUrl
        }

        const postedImgUrl = await addImg(newImgUrl, imgPool);

        res.status(200).send({
            status: 'success',
            data: postedImgUrl
        });

    } catch (error) {
        console.error('Error uploading file:', error);
        res.status(500).send('Error uploading file.');
    }
});

app.listen(port, () => {
    console.log(`Sampahku app listening on http://localhost:${port}`);
});
