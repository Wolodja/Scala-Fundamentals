package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x = 42
  println(x)

  // vals are immutable

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 1234
  val aLong: Long = 234234234233234223L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.42


  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects
}
