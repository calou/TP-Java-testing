package com.digimind.services.concept.extractor


import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BankSimulation extends Simulation {
  val accountCount = 0
  val httpProtocol = http.baseUrl("http://localhost:18888").header("Content-type", "application/json")

  val createAccountTemplate = "{\"account_id\":\"XXXXXXXXX\",\"balance\":10}"

  var randomCreateAccountJson = Iterator.continually(Map("json" -> (createAccountTemplate.replace("XXXXXXXXX", accountCount++))))

  val scn = scenario("CreateAccount")
    .feed(randomCreateAccountJson)
    .exec(http("Create")
      .put("/account")
      .body(StringBody("""${json}"""))
      .check(status.is(200)))
    .pause(5)
    /*
    .exec(http("List")
      .get("/account")
      .body(StringBody("""${json}"""))
      .check(status.is(200)))
    .pause(5)
*/
  setUp(scn.inject(
    atOnceUsers(5),
    rampUsers(10) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds),
    rampUsers(30) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds) randomized
  ).protocols(httpProtocol))
}