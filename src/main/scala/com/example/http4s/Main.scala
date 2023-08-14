package com.example.http4s

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {
  def run(args: List[String]) =
    Http4sServer.stream[IO].compile.drain.as(ExitCode.Success)
}
