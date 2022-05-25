package lectures.part2oop

object Generics extends App {

  class MyList[A] { // use the type A}

  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  class MyMap[Key, Value]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //List[Cat] extends List[Animal] ?
  // 1. yes = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList : CovariantList[Animal] = new CovariantList[Cat]
  //animalList.add(new Dog) ??? HARD QUESTION

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
}
