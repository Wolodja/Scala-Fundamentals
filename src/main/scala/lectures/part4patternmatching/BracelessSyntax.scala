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
}
