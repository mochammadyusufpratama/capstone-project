const { Storage } = require('@google-cloud/storage')

class GcpBucket {

    constructor() {
        this._storage = new Storage({
            projectId: 'capstoneproject-407212',
            keyFilename: 'credentials.json',
        })
    }

    async uploadImgToBucket(folder, file) {
        try {

            const bucketName = 'ch2-ps507-bucket';
            const fileName = `${folder}/${Math.random()}_${file.originalname}`

            const result = await this._storage.bucket(bucketName).file(fileName).save(file.buffer, {
                metadata: {
                    contentType: file.mimetype,
                },
            })

            const imageUrl = `https://storage.googleapis.com/${bucketName}/${fileName}`

            return imageUrl

        } catch (error) {

            console.error('Error uploading file to GCS:', error)
            throw error

        }
    }

    async deleteFileFromBucket(fileUrl) {

        try {

            const urlParts = fileUrl.split('/')
            const bucketName = urlParts[3] // Extract bucket name from URL
            const filePath = urlParts.slice(4).join('/')

            await this._storage.bucket(bucketName).file(filePath).delete()

            return true

        } catch (error) {

            console.error('Error deleting file:', error)
            return false

        }
    }
}

module.exports = GcpBucket 