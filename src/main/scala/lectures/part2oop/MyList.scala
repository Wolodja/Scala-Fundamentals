package lectures.part2oop

abstract class MyList[+A] {
  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with this element added
  toString => a string representation to the list
  */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${printElements}]"

  // higher order functions
  def filter(predicate: A => Boolean): MyList[A]
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]) : MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String = if (t.isEmpty) "" + h else s"$head ${tail.printElements}"

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }
  /*
  [1,2,3].map(n * 2)
   = new Cons(2, [2,3].map(n * 2))
   = new Cons(2, new Cons(4, [3].map(n * 2)))
   = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
   = new Cons(2, new Cons(4, new Cons(6, Empty)))
  */
  def map[B](transformer: A => B): MyList[B] = {
    new Cons(transformer(h), t.map(transformer))
  }
  /*
    [1,2] ++ [3,4,5]
   = new Cons(1, [2] ++ [3,4,5])
   = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
   = new Cons(1, new Cons(2, [3,4,5]))
  */
  def ++[B >: A](list: MyList[B]) : MyList[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2]flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
}


object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.add("element").toString)
  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }).toString)
  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }).toString)
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]]{
    override def apply(element: Int) = new Cons[Int](element, new Cons(element + 1, Empty))
  }).toString)
  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfIntegers == cloneListOfIntegers)
}
