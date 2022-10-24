package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMAps extends App {

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aSameTuple = (2, "Hello, Scala")

  println(aTuple._1)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phoneBook)

  // map cps
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functions on map
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toUpperCase -> pair._2))

  //filterKeys
  println(phoneBook.view.filterKeys(_.startsWith("D")).toMap)
  // mapValues
  println(phoneBook.view.mapValues("123" + _ * 10).toMap)

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel" -> 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  val someMap = Map("Jim" -> 500, "JIM" -> 999)
  println(someMap.map(pair => pair._1.toUpperCase -> pair._2))

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }


  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unFriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }


  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unFriend(networkAcc, friends.head, person))
    }

    removeAux(network(person), network) - person
  }

  def friendsNumber(network: Map[String, Set[String]], person: String): Int = {
    network(person).size
  }

  def personWithMostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  def countOfPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.view.filterKeys(key => network(key).isEmpty).size
  }

  def countOfPeopleWithNoFriendsBetter(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
        if(discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          if(person == target) true
          else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
          else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
        }
      }

    bfs(b, Set(), network(a) + a)
  }

  //  val empty: Map[String, Set[String]] = Map()
  val network = add(add(Map(), "Mary"), "Bob")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unFriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(Map(), "Bob"), "Mary"), "Jim")
  val testNet = friend(friend(people, "Bob", "Jim"), "Bob", "Mary")
  println(testNet)

  println(personWithMostFriends(testNet))
  println(countOfPeopleWithNoFriends(testNet))
  println(countOfPeopleWithNoFriendsBetter(testNet))

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(testNet, "Mary", "Bob"))
}
