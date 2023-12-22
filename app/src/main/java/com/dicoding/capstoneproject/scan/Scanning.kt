package com.dicoding.capstoneproject.scan

interface Scanning {
    fun onScanned(result: String)
    fun onError(e: Exception)
}
