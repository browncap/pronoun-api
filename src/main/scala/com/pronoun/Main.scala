package com.pronoun

import cats.effect._
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    Server.serve[IO].compile.drain.as(ExitCode.Success)
}
