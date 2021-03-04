package com.namrserver.routes

import com.google.gson.Gson
import com.namrserver.data.User
import com.namrserver.services.UserService
import io.ktor.application.call
import io.ktor.features.NotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

val gson = Gson()

fun Route.users() {

    val userService by di().instance<UserService>()
    /**
     *
     */
    get("") {
        call.respond(HttpStatusCode.OK)
    }

    get("users") {
        val allUsers = userService.getAllUsers()
        call.respond(allUsers)
    }

    post("user") {
        val userRequest = call.receive<User>()
        userService.addUser(userRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    patch("user/{id}") {
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        val userRequest = call.receive<User>()
        userService.updateUser(userId, userRequest)
        call.respond(HttpStatusCode.OK)
    }

    delete("user/{id}") {
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        userService.deleteUser(userId)
        call.respond(HttpStatusCode.OK)
    }
}