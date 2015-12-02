package com.akka.http.time

import akka.http.scaladsl.client.RequestBuilding
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{ShouldMatchers, FlatSpec}

class RouteTest extends FlatSpec
with ShouldMatchers
with ScalatestRouteTest
with JsonSupport {

  behavior of "The time service"
  "it" should "return current server time for GET requests to the root path" in {
    RequestBuilding.Get() ~> Server.route ~> check {
      status shouldBe StatusCodes.OK
      responseAs[Time].now should not be None
    }
  }
}
