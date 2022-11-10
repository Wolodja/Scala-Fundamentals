package lectures.part4patternmatching

object PatternsEverywhere extends App {

  // big idea #1
  try{

  } catch {
    case e: RuntimeException =>"runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES


  // big idea #2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0  // ?!
  } yield (x * 10)

  // generators are base on PATTERN MATCHING
  val tuples = List((1,2), (2,3))
  val filterTuples = for {
    (first, second) <- tuples
  } yield (first * second)
  // case classes, :: operators, ...

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // multiple values definition based on PM
  // ALL THE POWER

  val head :: tail = List(7,4,54)
  println(head)
  println(tail)

  // big idea #4
  // partial function based on PM
  val mappedList = list map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case notEven => notEven + " is not even"
  } // partial function match
  // equivalent to
  val mappedList2 = list map {
    x => x match
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case notEven => notEven + " is not even"
  }

  println(mappedList)
}
