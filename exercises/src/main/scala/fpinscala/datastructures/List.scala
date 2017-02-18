package fpinscala.datastructures

sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(head, tail) => tail
  }

  def setHead[A](l: List[A], h: A): List[A] = Cons(h, l)

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case n => l match {
      case Nil => Nil
      case Cons(_, tail) => drop(tail, n - 1)
    }
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t)(f)
    case _ => l
  }

  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => Nil
      case Cons(h, Cons(_, Nil)) => Cons(h, Nil)
      // case Cons(_,Nil) => Nil
      case Cons(h,t) => Cons(h,init(t))
    }

  def length[A](l: List[A]): Int =
    foldRight(l, 0)((_,acc) => acc + 1)

  /* Correct answer:
  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z 
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }*/

  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = {
    def loop (ll: List[A], acc: B): B = {
      ll match {
        case Nil => acc
        case Cons(head, tail) => loop(tail, f(acc, head))
      }
    }
    loop(l, z)
  }

  def sum3(ints: List[Int]): Int =
    foldLeft(ints, 0)(_ + _)

  def product3(ns: List[Double]) =
    foldLeft(ns, 1.0)(_ * _)

  def length2[A](l: List[A]): Int =
    foldLeft(l, 0)((acc, _) => acc + 1)

  def reverse[A](l: List[A]):List[A] =
    foldLeft(l, List[A]())((revL, i) => Cons(i, revL))

  def appendRight[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)(Cons(_, _))

  def appendAll[A](lists: List[List[A]]): List[A] =
    foldRight(lists, List[A]())(append)


  def plus1(l: List[Int]): List[Int] =
    foldRight(l, List[Int]())((i, newL) => Cons(i + 1, newL))

  def doublesToStr(l:List[Double]): List[String] = 
    foldRight(l, List[String]())((i, newL) => Cons(i.toString, newL))

  def map[A,B](l: List[A])(f: A => B): List[B] =
    foldRight(l, List[B]())((h, t) => Cons(f(h), t))

  def filter[A](l: List[A])(f: A => Boolean): List[A] =
    foldRight(l, Nil:List[A])((h, t) =>
      if (f(h)) Cons(h, t)
      else t
    )

  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =
    appendAll(map(l)(f))

  def filter2[A](l: List[A])(f: A => Boolean): List[A] =
    flatMap(l)(x => if (f(x)) List(x) else Nil)

  def plus(l1: List[Int], l2: List[Int]): List[Int] = combine(l1, l2)(_ + _)

  def combine[A](l1: List[A], l2: List[A])(f: (A, A) => A): List[A] = (l1, l2) match {
    case (_, Nil) => Nil
    case (Nil, _) => Nil
    case (Cons(h, t), Cons(h2, t2)) => Cons(f(h, h2), combine(t, t2)(f))
  }

  def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l, prefix) match {
    case (_, Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) if h1 == h2 => startsWith(t1, t2)
    case _ => false
  }

  def hasSubsequence[A](l: List[A], sub: List[A]): Boolean = l match {
    case Nil => sub == Nil
    case _ if startsWith(l, sub) => true
    case Cons(_, t) => hasSubsequence(t, sub)
  }
}
