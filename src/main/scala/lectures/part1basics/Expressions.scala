package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 //Expression
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3
  println(aVariable)

  //Instructions (DO) vs Expressions (VALUE)

  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 //IF expression

  println(if (aCondition) 5 else 3)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  } //Never write this again


  //Everything in Scala is an expression
  val aWeirdValue = (aVariable = 3) //Unit == void
  println(aWeirdValue)

  //side effects: println(), while, reassigning

  //Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }
}
