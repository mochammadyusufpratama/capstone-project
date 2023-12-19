const axios = require('axios');
const { searchEngine } = require('../../infrastructures/searchEngine')

class NewsHandler {
    async getAllNews(req, res) {
        try {
            // Search Query
            const query = 'penanganan sampah anorganik'

            const response = await axios.get(`${searchEngine.BASE_URL}?q=${query}&cx=${searchEngine.CX}&key=${searchEngine.API_KEY}`)

            res.json(response.data)

        } catch (error) {
            res.status(500).json({ error: error.message })
        }
    }
}

module.exports = { NewsHandler }