package com.fabiano.ioasys.data.remote.responses

data class LoginResponse(
    val errors: List<String>,
    val success: Boolean
)