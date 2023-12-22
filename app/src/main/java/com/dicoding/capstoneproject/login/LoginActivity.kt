package com.dicoding.capstoneproject.login

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.SessionManager
import com.dicoding.capstoneproject.launcher.LauncherActivity
import com.dicoding.capstoneproject.register.RegisterActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var otwregister: TextView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btn_login)
        otwregister = findViewById(R.id.otw_register)

        mAuth = FirebaseAuth.getInstance()


        editEmail.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editEmail, InputMethodManager.SHOW_IMPLICIT)

        editPassword.requestFocus()
        val amm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        amm.showSoftInput(editPassword, InputMethodManager.SHOW_IMPLICIT)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Silahkan Tunggu")
        progressDialog.setCancelable(false)

        otwregister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        progressDialog.show()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    reload()
                } else {
                    Toast.makeText(applicationContext, "Login gagal", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun reload() {
        val sessionManager = SessionManager(this)
        sessionManager.setLogin(true)

        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
    private fun logout() {
        val sessionManager = SessionManager(this)
        sessionManager.setLogin(false)

        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LauncherActivity::class.java))
        finish()
    }
}
