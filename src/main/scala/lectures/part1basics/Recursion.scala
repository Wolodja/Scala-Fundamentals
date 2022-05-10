package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }

  println(factorial(10))
  //println(factorial(5000))


  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) //Tail recursion = use recursive call as the LAST expression

    factHelper(n, 1)
  }
  /*
  anotherFactorial(10) = factHelper(10,1)
  = factHelper(9, 10*1)
  = factHelper(8, 9 * 10 *1)
  = factHelper(7, 8 * 9 * 10 * 1)
  = ...
  = factHelper(2, 3 * 4 * ... * 10 * 1)
  = factHelper(1, 2 * 3 * 4 * ... * 10 * 1)
  = 1 * 2 * 3 * 4 * ... * 10
  */

  println(anotherFactorial(20000))


  //When You need loops, use tail recursion

  def concatString(s: String, n: Int): String = {
    def concatStringHelper(s: String, n: Int, acc: String): String = {
      if (n <= 1) acc
      else concatStringHelper(s, n - 1, acc + s)
    }

    concatStringHelper(s, n, s)
  }

  println(concatString("abc ", 100))

  @tailrec
  def concatTailRec(aString: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatTailRec(aString, n - 1, aString + accumulator)
  }


  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (n <= 1) isStillPrime
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, true)
  }

  println(isPrime(2003))
  println(isPrime(629))


  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciHelper(n: Int, acc1: Int, acc2: Int): Int = {
      if (n <= 2) acc1 + acc2
      else fibonacciHelper(n - 1, acc2, acc1 + acc2)
    }

    fibonacciHelper(n, 0, 1)
    //1, 1, 2, 3, 5, 8, 13, 21
  }

  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(6))
  println(fibonacci(7))
  println(fibonacci(8))
  println(fibonacci(9))
}
