package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  val prepended2 = 42 +: aList :+ 89
  println(prepended)
  println(prepended2)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString(" | "))

  // arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numberSqe: Seq[Int] = numbers  // implicit conversion
  println(numberSqe)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val macCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    }   yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(macCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to macCapacity).toList
  val numbersVector = (1 to macCapacity).toVector

  // keep reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an etire 32-elements chunk
  println(getWriteTime(numbersVector))
}
