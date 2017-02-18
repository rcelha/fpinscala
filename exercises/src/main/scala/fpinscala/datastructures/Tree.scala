package fpinscala.datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


object Tree {
  def size[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(l, r) => size(l) + size(r) + 1
  }

  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(x) => x
    case Branch(l, r) => maximum(l) max maximum(r)
  }

  def depth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 0
    case Branch(l, r) => (depth(l) + 1) max (depth(r) + 1)
  }

  def map[A,B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }

  def fold[A,B](t: Tree[A])(l: A => B)(b: (B,B) => B): B = t match {
    case Leaf(n) => l(n)
    case Branch(ll,rr) => b(fold(ll)(l)(b), fold(rr)(l)(b))
  }

  def size2[A](t: Tree[A]):Int =
    fold(t)(_ => 1)(_ + _ + 1)

  def maximum2[A](t: Tree[Int]):Int =
    fold(t)(x => x)(_ max _)

  def depth2[A](t: Tree[A]):Int =
    fold(t)(x => 0)((l,r) => 1 + (l max r))

  def map2[A,B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)(l => Leaf(f(l)): Tree[B])(Branch(_,_))
}
