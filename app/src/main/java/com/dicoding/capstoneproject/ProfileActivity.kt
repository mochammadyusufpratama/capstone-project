package com.dicoding.capstoneproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.dicoding.capstoneproject.databinding.ActivityProfileBinding
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.imageProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startForResult.launch(intent)
        }

        loadUserData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedImageUri = result.data?.data
                selectedImageUri?.let { uploadImageToFirebaseStorage(it) }
            }
        }

    private fun uploadImageToFirebaseStorage(imageUri: Uri) {
        val storageRef = FirebaseStorage.getInstance().reference
        val currentUser = firebaseAuth.currentUser

        currentUser?.let { user ->
            val imageRef = storageRef.child("profile_images/${user.uid}")

            imageRef.putFile(imageUri)
                .addOnSuccessListener {
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(this).load(uri.toString()).into(binding.imageProfile)
                        updateProfileWithNewDisplayName(user, binding.editTextDisplayName.text.toString(), uri)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("ProfileActivity", "Gagal mengunggah gambar: ${e.message}")
                }
        }
    }

    private fun updateProfileWithNewDisplayName(user: FirebaseUser, newDisplayName: String, photoUri: Uri) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(newDisplayName)
            .setPhotoUri(photoUri)
            .build()

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ProfileActivity", "Profil pengguna berhasil diperbarui.")
                } else {
                    Log.e("ProfileActivity", "Gagal memperbarui profil pengguna: ${task.exception?.message}")
                }
            }
    }

    private fun loadUserData() {
        val currentUser = firebaseAuth.currentUser
        currentUser?.let { user ->
            binding.editTextDisplayName.setText(user.displayName ?: "Nama Pengguna")
            Glide.with(this).load(user.photoUrl).into(binding.imageProfile)
        }
    }
}
