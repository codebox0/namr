package com.namrserver.model


data class CreateUser(
    val name: String = "" ,
    val lastName: String = "",
    val email: String = "",
    val phone: String = ""
)
