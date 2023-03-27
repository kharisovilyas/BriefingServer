package com.edu.features.register

import kotlinx.serialization.Serializable
@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val password: String,
    val email: String,
    val name: String,
    val surname: String,
    val isTeacher: Boolean
)
@Serializable
data class RegisterResponseRemote(
    val token: String
)