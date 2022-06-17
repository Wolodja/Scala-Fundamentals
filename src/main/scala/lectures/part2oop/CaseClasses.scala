package lectures.part2oop

object CaseClasses extends App {
  /*
    equals, hashCode, toString
  */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.age)

  // 2. sensible toString
  // println(instance) = println(instance.toString)
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. Have companion object
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. Are serializable
  // Akka - sending objects through the network

  // 7. Have extractor patterns = can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}
