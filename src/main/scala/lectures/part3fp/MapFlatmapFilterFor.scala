package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations
  val numbers = List(1,2,3,4)
  val chars = List("a", "b", "c", "d")
  val colors = List("black", "white")
  val combinations = chars.flatMap(c => numbers.flatMap(n => colors.map(color => "" + c + n + " - " + color)))
  println(combinations)
}
