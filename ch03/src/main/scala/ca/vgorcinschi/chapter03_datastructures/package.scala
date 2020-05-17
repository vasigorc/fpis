package ca.vgorcinschi

import scala.annotation.tailrec

package object chapter03_datastructures {

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case x :: xs => f(x, foldRight(xs, z)(f))
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
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {

    @tailrec
    def helper(asRemainder: List[A], acc: B): B = asRemainder match {
      case Nil => acc
      case x :: Nil => f(acc, x)
      case x :: xs => helper(xs, f(acc, x))
    }

    helper(as, z)
  }

  /**
   * Exercise 3.12
   * Write a function that returns the reverse of a list. See if you can write it using a fold.
   */
  def reverse[A](as: List[A]): List[A] = foldLeft(as, List[A]())((acc, elem) => elem :: acc)

  /**
   * Exercise 3.13
   * Can you write [[foldLeft]] in terms of [[foldRight()]]? How about the other way around? Implementing
   * foldrRight via foldLeft is useful because it lets us implement foldRight tail-recursively, which means
   * it works even for large lists without overflowing the stack.
   */
  def foldLeftViaFoldRight[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    foldRight(reverse(as), z)((a, b) => f(b, a))
  }

  def foldRightViaFoldLeft[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(reverse(as), z)((b, a) => f(a, b))
  }

  /**
   * Exercise 3.14
   * Implement append in terms of either [[foldLeft()]] or [[foldRight()]]
   */
  def append[A](as: List[A], appended: A): List[A] = {
    foldLeft(reverse(as), List(appended))((acc, elem) => elem :: acc)
  }

  /**
   * Exercise 3.15
   * Write a function that concatenates a list of lists info a single list. Its runtime
   * should be linear in the total length of all lists. Try to use functions we have already
   * defined
   */
  def concatAll[A](lists: List[List[A]]): List[A] = {
    foldLeft(reverse(lists), List[A]())((acc, list) =>
      foldLeft(reverse(list), acc)((acc2, elem) => elem :: acc2))
  }
}