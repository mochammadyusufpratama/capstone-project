package com.dicoding.capstoneproject

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityReportBinding
import com.google.firebase.database.FirebaseDatabase

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private val databaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitReport.setOnClickListener {
            submitReport()
        }
    }

    private fun submitReport() {
        val reportDetails = binding.edtReportDetails.text.toString().trim()

        if (reportDetails.isNotEmpty()) {
            val report = Report(reportDetails, System.currentTimeMillis())
            val reportKey = databaseReference.child("reports").push().key

            reportKey?.let {
                databaseReference.child("reports").child(it).setValue(report)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showMessage("Laporan terkirim")
                            finish()
                        } else {
                            Log.e("ReportActivity", "Error: ${task.exception?.message}")
                        }
                    }
            } ?: showMessage("Gagal membuat kunci laporan")
        } else {
            showMessage("Isi detail laporan terlebih dahulu")
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    data class Report(val details: String, val timestamp: Long)
}
