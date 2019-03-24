package org.foo.bank

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BankSimulation extends Simulation {
  var accountCount = 0
  val httpProtocol = http.baseUrl("http://localhost:18080").header("Content-type", "application/json")

  val createAccountTemplate = "{\"account_id\":\"XXXXXXXXX\",\"balance\":10}"

  object Counter {
    def increment() = {
      accountCount = accountCount + 1
      "" + accountCount
    }
  }
  var randomCreateAccountJson = Iterator.continually(Map("json" -> (createAccountTemplate.replace("XXXXXXXXX", Counter.increment()))))

  val scn = scenario("CreateAccount")
    .feed(randomCreateAccountJson)
    .exec(http("Create")
      .put("/account")
      .body(StringBody("""${json}"""))
      .check(status.is(200)))
    .pause(5)
    .exec(http("List")
      .get("/account")
      .check(status.is(200)))
    .pause(5)

  setUp(scn.inject(
    atOnceUsers(5),
    rampUsers(10) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds),
    rampUsers(30) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds) randomized
  ).protocols(httpProtocol))
}