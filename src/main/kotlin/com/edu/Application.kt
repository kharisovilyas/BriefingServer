package com.edu

import com.edu.features.login.configureLoginRouting
import com.edu.features.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.edu.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSockets()
    configureHTTP()
    configureSerialization()
    configureRouting()
    configureRegisterRouting()
    configureLoginRouting()
}
