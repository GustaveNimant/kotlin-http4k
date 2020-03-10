package io.ipfs.kotlin

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.core.Status.Companion.OK

val app = { request: Request -> Response(OK).body("Hello, ${request.query("name")}!") }	

val jettyServer = app.asServer(Jetty(9000))
