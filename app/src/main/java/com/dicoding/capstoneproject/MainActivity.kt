package com.dicoding.capstoneproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.databinding.ActivityMainBinding
import com.dicoding.capstoneproject.homepage.ApiClient
import com.dicoding.capstoneproject.homepage.NewsAdapter
import com.dicoding.capstoneproject.homepage.ResponseNews
import com.dicoding.capstoneproject.launcher.LauncherActivity
import com.dicoding.capstoneproject.scan.BarcodeScanningActivity
import com.dicoding.capstoneproject.scan.NetworkUtils
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val cameraPermissionRequestCode = 1
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButtons()
        checkFirebaseUser()
        fetchNewsData()
    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(this)
        recyclerView.adapter = newsAdapter
    }

    private fun setupButtons() {
        binding.button1.setOnClickListener {
            startScanning()
        }

        binding.button2.setOnClickListener {
            openReportActivity()
        }

        binding.button3.setOnClickListener {
            openProfileActivity()
        }
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            uploadImageToApi(bitmap)
        }
    }

    private fun takePicture() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            takePictureLauncher.launch(null)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraPermissionRequestCode)
        }
    }


    private fun checkFirebaseUser() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser == null) {
            // Redirect ke LauncherActivity jika pengguna tidak terotentikasi
            val sessionManager = SessionManager(this)
            sessionManager.setLogin(false)

            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LauncherActivity::class.java))
            finish()
        }
    }

    private fun fetchNewsData() {
        lifecycleScope.launch {
            try {
                ApiClient.getNews().enqueue(object : Callback<ResponseNews> {
                    override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                newsAdapter.submitList(it.items)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                        // Handle failure
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun startScanning() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCameraWithScanner()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraPermissionRequestCode)
        }
    }

    private fun openCameraWithScanner() {
        val intent = Intent(this, BarcodeScanningActivity::class.java)
        startActivity(intent)
    }

    private fun openReportActivity() {
        val intent = Intent(this, ReportActivity::class.java)
        startActivity(intent)
    }

    private fun openProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == cameraPermissionRequestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCameraWithScanner()
        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
            startActivityForResult(intent, cameraPermissionRequestCode)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.logout)?.setOnMenuItemClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LauncherActivity::class.java))
            finish()
            true
        }
        return true
    }
    private fun uploadImageToApi(bitmap: Bitmap) {
        val imageFile = NetworkUtils.convertBitmapToFile(this, bitmap) // Tidak perlu casting 'this'
        NetworkUtils.uploadImage(imageFile, object : NetworkUtils.UploadCallback {
            override fun onSuccess(result: String) {
                Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_SCANNED_RESULT, result)
                    startActivity(this)
                }
            }

            override fun onFailure(e: IOException) {
                // Handle the error
            }
        })
    }
}

