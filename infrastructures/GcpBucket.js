const { Storage } = require('@google-cloud/storage')
class GcpBucket {
    constructor() {
        this._storage = new Storage({
            projectId: 'capstoneproject-407212', // Replace with your Google Cloud Project ID
            keyFilename: 'credentials.json', // Replace with the path to your service account key file
        })
    }

    async uploadImagToBucket(folder, file) {
        try {
            const bucketName = 'ch2-ps507-bucket'; // Replace with your Google Cloud Storage bucket name
            const fileName = `${folder}/${Math.random()}_${file.originalname}`; // Adjust the file path and name as needed

            const result = await this._storage.bucket(bucketName).file(fileName).save(file.buffer, {
                metadata: {
                    contentType: file.mimetype,
                },
            });

            const imageUrl = `https://storage.googleapis.com/${bucketName}/${fileName}`;
            return imageUrl;
        } catch (error) {
            console.error('Error uploading file to GCS:', error);
            throw error; // Rethrow the error for handling in the caller function
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
            // eslint-disable-next-line no-console
            console.error('Error deleting file:', error)
            return false
        }


    }
}
module.exports = GcpBucket 