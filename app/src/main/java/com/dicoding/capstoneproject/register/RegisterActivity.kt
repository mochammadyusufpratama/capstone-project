package com.dicoding.capstoneproject.register

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.login.LoginActivity
import com.dicoding.capstoneproject.login.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editPasswordConf: EditText
    private lateinit var btnRegister: Button
    private lateinit var hyperlinklogin: TextView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editName = findViewById(R.id.name)
        editEmail = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)
        editPasswordConf = findViewById(R.id.password_conf)
        btnRegister = findViewById(R.id.btn_register)
        hyperlinklogin = findViewById(R.id.hyperlink_login)

        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Silahkan Tunggu")
        progressDialog.setCancelable(false)

        hyperlinklogin.setOnClickListener { finish() }

        btnRegister.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val passwordConf = editPasswordConf.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConf.isNotEmpty()) {
                if (password == passwordConf) {
                    register(name, email, password)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Silahkan masukan password yang sama!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data!", Toast.LENGTH_SHORT).show()
            }
        }

        hyperlinklogin.setOnClickListener {
            if (editName.text.isNotEmpty() && editEmail.text.isNotEmpty() &&
                editPassword.text.isNotEmpty()
            ) {
                if (editPassword.text.toString() == editPasswordConf.text.toString()) {
                    register(
                        editName.text.toString(),
                        editEmail.text.toString(),
                        editPassword.text.toString(),
                    )
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Silahkan masukan password yang sama!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                hyperlinklogin.setOnClickListener {
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }
            }
        }
    }

    private fun register(name: String, email: String, password: String) {
        progressDialog.show()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    if (firebaseUser != null) {
                        saveUserDataToDatabase(firebaseUser.uid, name, email)
                        reload()
                    } else {
                        progressDialog.dismiss()
                        Toast.makeText(applicationContext, "Register Gagal. Pengguna tidak dibuat.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Register Gagal. ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun saveUserDataToDatabase(userId: String, name: String, email: String) {
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        val newUser = User(name, email)
        usersRef.child(userId).setValue(newUser)
    }

    private fun reload() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}
