package org.foo.bank

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BankSimulation extends Simulation {
  var accountCount = 0
  var transactionCount = 0

  val httpProtocol = http.baseUrl("http://localhost:18080").header("Content-type", "application/json")

  val createAccountTemplate = "{\"account_id\":\"XXXXXXXXX\",\"balance\":1000}"
  val createTransactionTemplate = "{\"debitor\":\"1\", \"creditor\": \"XXXXXXXXX\",\"category\": \"SALARY\", \"amount\":1}"

  object AccountCounter {
    def increment() = {
      accountCount = accountCount + 1
      "" + accountCount
    }
  }

  object TransactionCounter {
    def increment() = {
      transactionCount = transactionCount + 1
      "" + transactionCount
    }
  }

  var randomCreateAccountJson = Iterator.continually(
    Map(
      "accountJson" -> (createAccountTemplate.replace("XXXXXXXXX", AccountCounter.increment())),
      "transactionJson" -> (createTransactionTemplate.replace("XXXXXXXXX", TransactionCounter.increment()))
    )
  )
  val scn = scenario("Bank")
    .feed(randomCreateAccountJson)
    .exec(http("CreateAccount")
      .put("/account")
      .body(StringBody("""${accountJson}"""))
      .check(status.is(200)))
    .exec(http("ListAccounts")
      .get("/account")
      .check(status.is(200)))
    .pause(1)
    .exec(http("CreateTransaction")
      .put("/transaction")
      .body(StringBody("""${transactionJson}"""))
      .check(status.is(200)))


  setUp(scn.inject(
    atOnceUsers(5),
    rampUsers(10) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds),
    rampUsers(60) during (5 seconds),
    constantUsersPerSec(20) during (15 seconds) randomized
  ).protocols(httpProtocol))
}