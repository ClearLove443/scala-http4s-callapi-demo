package com.example.http4s
import cats.effect.Concurrent
import cats.implicits._
import io.circe.Decoder
import io.circe.Encoder
import io.circe.generic.semiauto._
import org.http4s.Method._
import org.http4s._
import org.http4s.circe._
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.implicits._

trait ClientDemo[F[_]] {
  def get: F[String]
}

object ClientDemo {

  def impl[F[_]: Concurrent](C: Client[F]): ClientDemo[F] = new ClientDemo[F] {
    val dsl = new Http4sClientDsl[F] {}
    import dsl._
    def get: F[String] = {
      C.expect[String](GET(uri"http://127.0.0.1:18080/hello/clientdemo"))
    }
  }
}
