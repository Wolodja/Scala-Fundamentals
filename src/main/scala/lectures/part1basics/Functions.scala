package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int) = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())

  def aRepeatedFunction(a: String, n: Int): String = {
    if (n == 1) a
    else a + aRepeatedFunction(a, n - 1)
  }

  println(aRepeatedFunction("hello", 3))


  //When You need loops use recursion

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  def aFactorial(n: Int): Int = {
    if (n <= 1) n
    else n * (aFactorial(n - 1))
  }

  println(aFactorial(5))

  def fibonacci(n: Int): Int =
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)


  println(fibonacci(8))


  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(2003))
  println(isPrime(37 * 17))

}
