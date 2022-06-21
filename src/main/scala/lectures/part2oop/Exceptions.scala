package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //println(x.length) // this will crash with NPE

  // 1. throwing and catching exceptions
  //val aWeirdValue: String = throw new NullPointerException
  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // 2. hot to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int fo you")
    else 42

  val potentialFail = try {
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. hot to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  throw exception
}
