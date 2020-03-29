package ca.vgorcinschi

import scala.reflect.ClassTag

package object chapter02_gettingstarted {

  // Exercise 2.2 Implement isSorted, which checks whether an Array[A] is sorted according
  //to a given comparison function:
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {

    if (as.length < 2) return true

    @scala.annotation.tailrec
    def loop(head: A, remainder: Array[A]): Boolean = remainder match {
      case Array() => true
      case Array(x, _*) => if (ordered(head, x)) loop(x, remainder.tail) else false
    }

    loop(as.head, as.tail)
  }
}
