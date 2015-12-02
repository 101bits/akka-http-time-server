package com.akka.http.time

import java.time.LocalDateTime

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import spray.json._

import scala.io.StdIn.readLine

final case class Time(now: String = LocalDateTime.now().toString)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val timeFormat = jsonFormat1(Time)
}


object Server extends JsonSupport {
  val route =
    pathSingleSlash {
      get {
        complete {
          Time()
        }
      }
    }

  def main(args: Array[String]) {
    implicit val system = ActorSystem("timeServer")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val ec = system.dispatcher

    val serverFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"TimeServer is running at http://localhost:8080\nPress RETURN to stop ...")
    readLine()

    serverFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}

