package com.pronoun

import cats.effect.{ConcurrentEffect, ContextShift, ExitCode, Timer}
import com.pronoun.config.ConfigService
import org.http4s.server.blaze.BlazeServerBuilder
import fs2._
import org.http4s.implicits._
import org.http4s.server.middleware.CORS

object Server {

  def serve[F[_]](implicit F: ConcurrentEffect[F], CS: ContextShift[F], T: Timer[F]): Stream[F, ExitCode] =
    for {
      config <- Stream.eval(ConfigService.getConfig[F])
      routes = CORS(PronounRoutes.routes[F]).orNotFound
      server <- BlazeServerBuilder[F].bindHttp(config.http.port, config.http.host).withHttpApp(routes).serve
    } yield server

}