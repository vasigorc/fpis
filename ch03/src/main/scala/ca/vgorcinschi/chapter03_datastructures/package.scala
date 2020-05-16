package ca.vgorcinschi

package object chapter03_datastructures {

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case x::xs => f(x, foldRight(xs, z)(f))
  }

  // Exercise 3.9 Compute the length of a list using foldRight
  def length[A](as: List[A]): Int = {
    foldRight(as, 0)((_, acc) => acc + 1)
  }
}