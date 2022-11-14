package lectures.part4patternmatching

object BracelessSyntax extends App {

  // if -expression
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java - style
  val anIfExpression_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2 > 3 then
      "bigger"   // higher indentation then if part
    else
      "smaller"

  println(anIfExpression_v4)

  // scala 3 one-liner
  val anFiExpression_v5 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("black", "white")
  } yield s"$n$s"

  val aForComprehension_v2 = for
    n <- List(1, 2, 3)
    s <- List("black", "white")
  yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40


    partialResult + 2


  println(computeMeaningOfLife(78))

  // class definition (same for traits, objects, enums etc)
  class Animal:
    def eat(): Unit =
      println("I'm eating")
    end eat

    def grow(): Unit =
      println("I'm growing")

  // 3000 more lines of code
  end Animal

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces and 2 tabs
  // 3 spaces + 2 tabs > 3 spaces and 1 tabs
  // 3 spaces + 2 tabs ??? 2 spaces and 3 tabs
}
