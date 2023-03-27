package com.edu.plugins

import io.ktor.server.plugins.conditionalheaders.*
import io.ktor.server.application.*

fun Application.configureHTTP() {
    install(ConditionalHeaders)
}
