const axios = require('axios');
const { searchEngine } = require('../../infrastructures/searchEngine')

class NewsHandler {
    async getAllNews(req, res) {
        try {
            // Search Query
            const query = 'penanganan sampah dengan efektif'

            const response = await axios.get(`${searchEngine.BASE_URL}?q=${query}&cx=${searchEngine.CX}&key=${searchEngine.API_KEY}`)

            res.json(response.data)

        } catch (error) {
            res.status(500).json({ error: error.message })
        }
    }

    async getBasedNews(req, res) {
        try {
            // Search Query
            const type = req.query.tipe
            const query = `penanganan sampah ${type} dengan efektif`

            const response = await axios.get(`${searchEngine.BASE_URL}?q=${query}&cx=${searchEngine.CX}&key=${searchEngine.API_KEY}`)

            const output = {
                tipe: type,
                data: response.data
            }

            res.json(output)

        } catch (error) {
            res.status(500).json({ error: error.message })
        }
    }
}

module.exports = { NewsHandler }