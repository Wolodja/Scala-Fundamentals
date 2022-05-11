package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFactorial(n - 1, n * acc)
  }

  val factorial10 = trFactorial(10)

  def savePicture(format: String = "jpeg", width: Int = 1920, height: Int = 1080): Unit = println("saving")

  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "bmp")
}
