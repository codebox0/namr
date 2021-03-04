package com.namrserver


import com.namrserver.config.initDB
import com.namrserver.routes.apiRoute
import com.namrserver.routes.users
import com.namrserver.services.bindServices
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {


    initDB()

    install(ContentNegotiation) { gson { } }
    install(CallLogging)

    install(CORS)
    install(StatusPages) {
        exception<EntityNotFoundException> {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    di {
        bindServices()
    }

    routing {
        apiRoute()
        users()
    }

}


data class Response(val status: String)