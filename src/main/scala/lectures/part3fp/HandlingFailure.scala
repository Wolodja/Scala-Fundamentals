package lectures.part3fp

import java.util.Random
import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod() : String = throw new RuntimeException("No string for You")

  // Try objects via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)
  println(potentialFailure.isFailure)

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))

  // IF you design the API
  def betterUnsafeMethod() : Try[String] = Failure(new RuntimeException())
  def betterBackupMethod() : Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 2)))
  println(aSuccess.filter(_ > 10))

  // for-comprehensions

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  Try(HttpService.getConnection(hostname, port))
    .map(_.get("/home"))
    .foreach(renderHTML)

  // for-comprehension
  for {
    connection <- Try(HttpService.getConnection(hostname, port))
    html <- Try(connection.get("home"))
  } renderHTML(html)
}
