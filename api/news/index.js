const express = require('express');
const router = express.Router();
const { NewsHandler } = require('./handler')

const newsHandler = new NewsHandler();

router.get('/news', async (req, res) => {
    await newsHandler.getAllNews(req, res)
});

module.exports = router