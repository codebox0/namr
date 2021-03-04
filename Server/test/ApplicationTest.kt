package de.novatec

import com.namrserver.module
import io.ktor.http.*

import com.google.gson.Gson
import io.ktor.server.testing.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() {
        withTestApplication() {
            handleRequest(HttpMethod.Get, "api/v1/users").apply {
                print(response.content)
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }


    @Test
    fun testAddData() {
        withTestApplication() {
                val user =     mapOf(
                        "name" to "Ibrahimovic",
                        "lastName" to "Zlatan",
                        "email" to "test@gmail.com",
                        "phone" to "0102030405"
                    )
            val jsuser =
                Gson().toJson(user)

            println(jsuser)
            handleRequest(HttpMethod.Post, "api/v1/user")
            {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(
                    jsuser
                )
            }.apply {
                assertEquals(HttpStatusCode.Accepted, response.status())
            }
        }
    }

}