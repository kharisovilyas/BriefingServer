package com.edu.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import kotlinx.serialization.Serializable


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("New hello world")
        }
    }
}
