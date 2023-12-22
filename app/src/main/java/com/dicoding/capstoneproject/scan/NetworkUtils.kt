package com.dicoding.capstoneproject.scan

import android.content.Context
import android.graphics.Bitmap
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object NetworkUtils {

    fun uploadImage(imageFile: File, callback: UploadCallback) {
        // Gunakan MultipartBody untuk mengirim file
        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("photo", imageFile.name, requestBody)
            .build()

        val request = Request.Builder()
            .url("https://first-deploy-6xu7fd3j2q-et.a.run.app/upload")
            .post(multipartBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    callback.onSuccess(responseBody)
                } else {
                    callback.onFailure(IOException("Unexpected response"))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e)
            }
        })
    }

    interface UploadCallback {
        fun onSuccess(result: String)
        fun onFailure(e: IOException)
    }

    fun convertBitmapToFile(context: Context, bitmap: Bitmap): File {
        val file = File(context.cacheDir, "scanned_image.jpg") // Perubahan di sini
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val bitmapData = bos.toByteArray()

        FileOutputStream(file).use { fos ->
            fos.write(bitmapData)
            fos.flush()
        }

        return file
    }
}
