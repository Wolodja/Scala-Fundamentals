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

  // hofs
  def foreach(sideEffect: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
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

  // hofs
  def foreach(sideEffect: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
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


  // hofs
  def foreach(sideEffect: A => Unit): Unit = {
    sideEffect(h)
    t.foreach(sideEffect)
  }
  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A]=
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if(compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))

    var sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
}


object ListTest extends App {
  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyList[String] = new Cons("a", new Cons("b", new Cons("c", Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
  println(listOfIntegers.add("element").toString)
  println(listOfIntegers.map(_ * 2).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)

  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, new Cons(6, Empty)))
  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap((element: Int) => new Cons[Int](element, new Cons(element + 1, Empty))).toString)

  val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfIntegers == cloneListOfIntegers)

  val printElements = (x: Int) => println(x)
  listOfIntegers.foreach(printElements)
  println(listOfIntegers.sort((x, y) => y - x))
}
