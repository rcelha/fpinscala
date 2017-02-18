package fpinscala.datastructures

object Main {
  def main (args: Array[String]) {
    println("Main")

    println("# append")
    println(List.append(List(1,2,3), List(4,3,2,1)))

    println("# tail")
    val a:List[Int] = List(1,2,3,4,5,6)
    val b = List.tail(a)
    List.tail(Nil)
    println(b)

    println("# setHead")
    val c = List.setHead(a, 99)
    println(List.setHead(Nil, 99))

    println("# drop")
    println(List.drop(Nil, 10))
    println(List.drop(c, 0))
    println(List.drop(c, 1))
    println(List.drop(c, 2))
    println(List.drop(c, 3))

    println("# dropWhile")
    println(List.dropWhile(a)(x => x < 99))
    println(List.dropWhile(a)(x => x < 3))
    println(List.dropWhile(a)(x => x != 5))

    println("# init")
    println(List.init(a))
    println(List.init(List(1,2,3)))
    println(List.init(List(1,2)))

    println("# sum2")
    println(List.sum2(List(1,2,3,4)))
    println(List.sum2(List(1,2,3)))

    println("# product2")
    println(List.product2(List(1,2,3,4)))
    println(List.product2(List(1,2,3)))
    println(List.product2(List(1.0,0.0)))

    val foo = List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_))
    println(foo)

    println("# length")
    println(List.length(List(1,2,3,4)))
    println(List.length(List(1,2,3)))
    println(List.length(List(1.0,0.0)))

    println("# foldLeft")
    println(List.foldLeft(List(1,2,3,4), 0)(_ + _))

    println("# sum3")
    println(List.sum3(List(1,2,3,4)))
    println(List.sum3(List(1,2,3)))

    println("# product3")
    println(List.product3(List(1,2,3,4)))
    println(List.product3(List(1,2,3)))
    println(List.product3(List(1.0,0.0)))

    println("# length2")
    println(List.length2(List(1,2,3,4)))
    println(List.length2(List(1,2,3)))
    println(List.length2(List(1.0,0.0)))

    println("# reverse")
    println(List.reverse(List(1,2,3,4)))

    println("# appendRight")
    println(List.appendRight(List(1,2,3), List(4,5,6)))

    println("# appendAll")
    println(List.appendAll(List(List(1,2,3), List(4,5,6), List(7,8,9))))

    println("# plus1")
    println(List.plus1(List(1,2,3,4)))

    println("# toString")
    println(List.doublesToStr(List(1.000001,2.0,3.4)))

    println("# map")
    println(List.map(List(1,2,3,4))(_ * 2))

    println("# filter")
    println(List.filter(List(1,2,3,4))(_ % 2 == 0))

    println("# flatMap")
    println(List.flatMap(List(1,2,3,4))(i => List(i, i * i)))

    println("# filter2")
    println(List.filter2(List(1,2,3,4))(_ % 2 == 0))

    println("# plus")
    println(List.plus(List(1,2,3), List(4,5,6)))

    println("# combine")
    println(List.combine(List(1,2,3), List(4,5,6))(_ + _))

    println("# hasSubsequence")
    println(List.hasSubsequence(List(1,2,3,4), List(1)))
    println(List.hasSubsequence(List(1,2,3,4), List(1,2)))
    println(List.hasSubsequence(List(1,2,3,4), List(2,3)))
    println(List.hasSubsequence(List(1,2,3,4), List(3,4)))
    println(List.hasSubsequence(List(1,2,3,4), List(4)))
    println(List.hasSubsequence(List(1,2,3,4), List(5)))
    println(List.hasSubsequence(List(1,2,3,4), List(3,4,5)))

    // Trees
    val t1 = Branch[Int](
      Branch(Leaf(1), Branch(Leaf(2), Leaf(5))),
      Branch(Leaf(4), Leaf(3))
    )
    println("### TREE ###")
    println(t1)

    println(" #size")
    println(Tree.size(t1))
    println(Tree.size2(t1))

    println(" #maximum")
    println(Tree.maximum(t1))
    println(Tree.maximum2(t1))

    println(" #depth")
    println(Tree.depth(t1))
    println(Tree.depth2(t1))

    print(" #map")
    println(Tree.map(t1)(_ + 1))
    println(Tree.map2(t1)(_ + 1))
    println(Tree.map(t1)("#" + _.toString))
    println(Tree.map2(t1)("#" + _.toString))
  }
}
