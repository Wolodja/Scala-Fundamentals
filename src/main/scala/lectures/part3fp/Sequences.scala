package lectures.part3fp

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  val prepended2 = 42 +: aList :+ 89
  println(prepended)
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString(" | "))
}
