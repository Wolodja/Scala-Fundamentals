package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))
  // function types = Function1[A,B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("3") + 5)
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes and int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
  */
  val concat: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  val supperAdder : Int => (Int => Int) = new Function1[Int, Function1[Int, Int]]{
    override def apply(x: Int): Int => Int = new Function[Int, Int]{
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = supperAdder(3)
  println(adder3(4))
  println(supperAdder(3)(4)) // curried function

}

class MyFunction[A, B] {
  def apply(element: A): B = ???
}
