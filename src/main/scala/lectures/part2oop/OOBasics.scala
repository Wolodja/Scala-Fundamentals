package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Charles", " Dickens", 1812)
  val imposter = new Writer("Charles", " Dickens", 1812)
  val novel = new Novel("Great Expactations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.increment.increment.increment.print
  counter.increment(10).print

}

//constructor
class Person(name: String, val age: Int) {
  //body
  val x = 2
  println(1 + 3)

  //method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  def this(name: String) = this(name, 0)

}

//class parameters are NOT FIELDS


class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + surname;
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}

class Counter(val count: Int = 0) {
  def increment = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }

  def decrement = {
    println("decrementing")
    new Counter(count - 1)
  }

  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment.increment(n - 1)
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement.decrement(n - 1)
  }

  def print = println(count)
}