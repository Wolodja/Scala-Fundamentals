package lectures.part3fp

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  // unsafe API
  def unsafeMethod(): String = null
  // val result = Some(null) //WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainResult)


}
