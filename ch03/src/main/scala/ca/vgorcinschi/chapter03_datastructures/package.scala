package ca.vgorcinschi

import scala.annotation.tailrec

package object chapter03_datastructures {

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case x::xs => f(x, foldRight(xs, z)(f))
  }

  // Exercise 3.9 Compute the length of a list using foldRight
  def length[A](as: List[A]): Int = {
    foldRight(as, 0)((_, acc) => acc + 1)
  }

  /**
   * Exercise 3.10 Our implementation of [[foldRight()]] is not [[annotation.tailrec]] and will result in a
   * [[StackOverflowError]] for large lists (we say it's not stack safe). Convince yourself that this is the
   * ase, and then write another general list-recursion function, foldLeft, that is tail-recursive, using techniques
   * we discussed in the previous chapter. Here is the signature
   */
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = {

    @tailrec
    def helper(asRemainder: List[A], acc: B): B = asRemainder match {
      case Nil => acc
      case x::Nil => f(acc, x)
      case x::xs => helper(xs, f(acc, x))
    }

    helper(as, z)
  }
}