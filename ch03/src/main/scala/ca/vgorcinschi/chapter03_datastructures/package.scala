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
    foldRightViaFoldLeft(as, List(appended))((elem, acc) => elem :: acc)
  }

  /**
   * Exercise 3.15
   * Write a function that concatenates a list of lists info a single list. Its runtime
   * should be linear in the total length of all lists. Try to use functions we have already
   * defined
   */
  def concatAll[A](lists: List[List[A]]): List[A] = {
    foldRightViaFoldLeft(lists, List[A]())((list, acc) =>
      foldRightViaFoldLeft(list, acc)((elem, acc2) => elem :: acc2))
  }

  /**
   * Exercise 3.18
   * Write a function map that generalizes modifying each element in a list while
   * maintaining the structure of the list.
   */
  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldRightViaFoldLeft(as, List[B]())((elem, acc) => f(elem) :: acc)
  }

  /**
   * Exercise 3.20
   * Write a function flatMap that works like [[map()]] except that the function given will return
   * a list instead of a single result, and that list should be inserted into the final resulting list
   */
  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = concatAll(map(as)(f))

  /**
   * Exercise 3.23
   * Generalize the function you just wrote so that it's not specific to integers or addition. Name your generalized
   * function zipWith
   *
   * @note the axis is the as param, the final list is only guaranteed to have values based on
   *       its values
   */
  def zipWith[A, B, C](as: List[A], bs: List[B])(f: (A, B) => C): List[C] = (as, bs) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (h1 :: t1, h2 :: t2) => f(h1, h2) :: zipWith(t1, t2)(f)
  }

  /**
   * Exercise 3.24 Implement [[hasSubsequence()]] for checking whether a List contains another
   * List as a subsequence. For instance, List(1,2,3,4) would have List(1,2), List(2,3) and List(4)
   * as subsequences, among others.
   *
   * @param targetList
   * @param searchList
   * @tparam A
   * @return
   */
  def hasSubsequence[A](targetList: List[A], searchList: List[A]): Boolean = (targetList, searchList) match {
    case (Nil, Nil) => true
    case (_, Nil) => true
    case (Nil, _) => false
    case (_, _) => kSubsequences(targetList, searchList.size).contains(searchList)
  }

  /**
   *
   * @param list - target list
   * @param k - windowSize
   * @tparam A - type param of target list
   * @return list of all subsequences of target list
   */
  def kSubsequences[A](list: List[A], k: Int): List[List[A]] = {
    if (list.isEmpty || k > list.size) return Nil
    // define from to limits
    val (from, to) = (0, list.size - k)
    foldLeftViaFoldRight((from to to).toList, List.empty[List[A]])((acc, offset) =>
      list.slice(offset, offset + k) :: acc)
  }
}
