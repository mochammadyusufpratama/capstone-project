package com.dicoding.capstoneproject

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityDetailBinding
import java.io.File

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SCANNED_RESULT = "extra_scanned_result"
        const val EXTRA_IMAGE_PATH = "extra_image_path"

        @JvmStatic
        fun start(context: Context, scannedResult: String, imagePath: String?) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_SCANNED_RESULT, scannedResult)
                putExtra(EXTRA_IMAGE_PATH, imagePath)

            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scannedResult = intent.getStringExtra(EXTRA_SCANNED_RESULT) ?: "No data"
        val imagePath = intent.getStringExtra(EXTRA_IMAGE_PATH)

        binding.tvStoryDescription.text = scannedResult
        binding.tvStoryExplanation.text = "Hasil Scan: $scannedResult"

        imagePath?.let { displayImage(it) }
    }

    private fun displayImage(imagePath: String) {
        val imgFile = File(imagePath)
        if (imgFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            binding.imgStory.setImageBitmap(bitmap)
        }
    }
}

