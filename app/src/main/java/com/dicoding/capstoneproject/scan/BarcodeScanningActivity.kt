package com.dicoding.capstoneproject.scan

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.dicoding.capstoneproject.DetailActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivityBarcodeScanningBinding
import com.dicoding.capstoneproject.scan.Scanning
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BarcodeScanningActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBarcodeScanningBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageCapture: ImageCapture
    private lateinit var progressBar: ProgressBar

    companion object {
        private const val TAG = "BarcodeScanningActivity"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeScanningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = findViewById(R.id.progress_bar)

        cameraExecutor = Executors.newSingleThreadExecutor()
        setupCamera()

        binding.buttoncamera.setOnClickListener {
            takePhoto()
        }
    }

    private fun setupCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()


            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    @SuppressLint("SimpleDateFormat")
    private fun takePhoto() {
        progressBar.visibility = View.VISIBLE
        val photoFile = File(
            externalMediaDirs.firstOrNull(),
            SimpleDateFormat(FILENAME_FORMAT).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, msg)

                    // Unggah foto ke API
                    NetworkUtils.uploadImage(photoFile, object : NetworkUtils.UploadCallback {
                        override fun onSuccess(result: String) {
                            runOnUiThread {
                                progressBar.visibility = View.GONE
                                // Jika berhasil, buka DetailActivity dengan hasilnya
                                DetailActivity.start(
                                    this@BarcodeScanningActivity,
                                    result,
                                    savedUri.toString()
                                )
                            }
                        }

                        override fun onFailure(e: IOException) {
                            progressBar.visibility = View.GONE
                            // Handle error
                            Log.e(TAG, "Upload failed: ${e.message}")
                            Toast.makeText(
                                this@BarcodeScanningActivity,
                                "Upload failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
