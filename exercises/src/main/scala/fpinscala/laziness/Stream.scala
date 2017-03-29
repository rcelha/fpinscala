package fpinscala.laziness

import Stream._
trait Stream[+A] {
  def foldRight[B](z: => B)(f: (A, => B) => B): B = // The arrow `=>` in front of the argument type `B` means that the function `f` takes its second argument by name and may choose not to evaluate it.
    this match {
      case Cons(h,t) => f(h(), t().foldRight(z)(f)) // If `f` doesn't evaluate its second argument, the recursion never occurs.
      case _ => z
    }

  def exists(p: A => Boolean): Boolean = 
    foldRight(false)((a, b) => p(a) || b) // Here `b` is the unevaluated recursive step that folds the tail of the stream. If `p(a)` returns `true`, `b` will never be evaluated and the computation terminates early.

  @annotation.tailrec
  final def find(f: A => Boolean): Option[A] = this match {
    case Empty => None
    case Cons(h, t) => if (f(h())) Some(h()) else t().find(f)
  }

  def toList: List[A] =
    this match {
      case Empty => List()
      case Cons(h, t) => h() :: t().toList
    }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n == 1 => cons(h(), empty)
    case Cons(h, t) if n > 1 => cons(h(), t().take(n-1))
    case _ => Empty
  }

  def drop(n: Int): Stream[A] = sys.error("todo")

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case Cons(h, t) if !p(h()) => cons(h(), empty)
    case _ => Empty
  }

  def foldedTakeWhile(p: A => Boolean): Stream[A] =
    foldRight(Empty:Stream[A])((a,b) =>
        if (p(a)) cons(a, b)
        else empty)

  def forAll(p: A => Boolean): Boolean =
    foldRight(true)((a,b) => p(a) && b)

  def headOption: Option[A] = sys.error("todo")

  // 5.7 map, filter, append, flatmap using foldRight. Part of the exercise is
  // writing your own function signatures.
  def map[B](f: A => B): Stream[B] =
    foldRight(Empty:Stream[B])((a, b) => cons(f(a), b))

  def filter(f: A => Boolean): Stream[A] =
    foldRight(Empty:Stream[A])((a, b) =>
        if (f(a)) cons(a, b)
        else b)

  def append[B>:A](x: => Stream[B]): Stream[B] =
    foldRight(x)((a, b) => cons(a, b))


  def flatMap[B>:A](f: A => Stream[B]): Stream[B] =
    foldRight(Empty:Stream[B])((a, b) => f(a) append b)

  def mapByUnfold[B](f: A => B): Stream[B] =
    unfold(this) {
      case Cons(h, t) => Some(f(h()), t())
      case _ => None
    }

  def takeByUnfold(n: Int): Stream[A] =
    unfold((this, n)) {
      case (_, 0) => None
      case (Cons(h, t), nn) => Some(h(), (t(), nn -1))
      case _ => None
    }
  
  def takeWhileByUnfold (p: A => Boolean): Stream[A] =
    unfold(this) {
      case Cons(h, t) if p(h()) => Some(h(), t())
      case _ => None
    }
  
  def zip [B] (bs: Stream[B]): Stream[(A,B)] =
    unfold((this, bs)) {
        case (Cons(aH, aT), Cons(bH, bT)) => Some(((aH(), bH()), (aT(), bT())))
        case _ => None
    }

  def zipAll [AA>:A, B] (bs: Stream[B], defaultA:AA, defaultB:B): Stream[(AA,B)] =
    unfold((this, bs)) {
        case (Empty, Cons(bH, bT)) => Some(((defaultA, bH()), (Empty, bT())))
        case (Cons(aH, aT), Empty) => Some(((aH(), defaultB), (aT(), Empty)))
        case (Cons(aH, aT), Cons(bH, bT)) => Some(((aH(), bH()), (aT(), bT())))
        case _ => None
    }



  def startsWith[B](s: Stream[B]): Boolean = sys.error("todo")
}
case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty 
    else cons(as.head, apply(as.tail: _*))

  val ones: Stream[Int] = Stream.cons(1, ones)

  def constant[A](a: A): Stream[A] = cons(a, constant(a))

  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  def fibs(): Stream[Int] = {
    def loop (a:Int, b:Int): Stream[Int] = {
      cons(a, loop(b, a + b)) 
    }
    loop(0, 1)
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) match {
        case Some((a, s)) => cons(a, unfold(s)(f))
        case None => Empty
    }

  def fibsByUnfold() = 
    unfold ((0, 1))(a => Some(a._1, (a._2, a._1 + a._2)))

  def fromByUnfold(n: Int): Stream[Int] = unfold(n)(a => Some(a, a+1))

  def constantByUnfold[A](a: A): Stream[A] = unfold(a)(aa => Some(aa, aa))
}
