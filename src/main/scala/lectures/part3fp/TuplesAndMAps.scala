package lectures.part3fp

object TuplesAndMAps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello, Scala")  // Tuple2[Int, String] = (Int, String)
  val aSameTuple = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // ("Hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phoneBook)

  // map cps
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functions on map
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toUpperCase -> pair._2))

  //filterKeys
  println(phoneBook.view.filterKeys(_.startsWith("D")).toMap)
  // mapValues
  println(phoneBook.view.mapValues("123" + _ * 10).toMap)

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel" -> 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
