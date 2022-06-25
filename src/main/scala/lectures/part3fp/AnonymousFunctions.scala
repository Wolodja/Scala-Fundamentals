package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  // multiple params in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething = () => 3;

  // careful
  println(justDoSomething)
  println(justDoSomething())
}
