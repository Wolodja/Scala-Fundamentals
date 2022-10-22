package lectures.part3fp

object TuplesAndMAps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello, Scala")  // Tuple2[Int, String] = (Int, String)
  val aSameTuple = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // ("Hello, Scala", 2)
}
