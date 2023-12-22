package com.dicoding.capstoneproject.login

data class User(
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null
) {
    constructor() : this("", "", "")
}