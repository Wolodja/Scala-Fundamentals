package lectures.part2oop

object Objects {

  //SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  object Person { //type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    def apply(mother: Person, father: Person) = new Person("Boby")
  }

  class Person(val name: String) {
    //instance-level functionality
  }
  //COMPANIONS

  def main(args: Array[String]): Unit = {

    println(Person.N_EYES)
    println(Person.canFly)

    //Scala object = SINGLETON INSTANCE
    val mary = Person
    val john = Person
    println(mary == john)

    val person1 = new Person("Mary")
    val person2 = new Person("John")
    println(person1 == person2)

    val bobbie = Person(person1, person2)
  }
  //Scala applications = scala object with
  // def main(args: Array[String]): Unit
}
