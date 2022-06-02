package lectures.part2oop

object AnonymousClasses extends App {
  abstract trait Animal {
    def eat: Unit
  }
  // anonymous class
  val funnyAnimal: Animal = new Animal :
    override def eat: Unit = println("hahaha")

  /*
  equivalent with

  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("hahaha")
  }
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1
  */

  println(funnyAnimal.getClass)
  println(funnyAnimal.eat)



  class Person(name: String){
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
}
