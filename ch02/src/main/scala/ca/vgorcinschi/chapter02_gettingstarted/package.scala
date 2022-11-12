package ca.vgorcinschi

import scala.reflect.ClassTag

package object chapter02_gettingstarted {

  // Exercise 2.2 Implement isSorted, which checks whether an Array[A] is sorted according
  //to a given comparison function:
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {

    if (as.length < 2) return true

    @scala.annotation.tailrec
    def loop(head: A, remainder: Array[A]): Boolean = remainder match {
      case Array()      => true
      case Array(x, _*) => if (ordered(head, x)) loop(x, remainder.tail) else false
    }

    loop(as.head, as.tail)
  }

  // Exercise 2.3 Let's look at another example, currying, which converts a function f of two arguments into
  // a function of one argument that partially applies f.
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = a => b => f(a, b)

  // Exercise 2.4 Implement uncurry, which reverses the transformation of curry. Note that since =>
  // associates to the right, A => (B => C) can be written as A => B => C
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

  // Exercise 2.5 Implement the higher-order function that composes two functions
  def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))
}
