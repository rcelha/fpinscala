package fpinscala.datastructures

object Main {
  def main (args: Array[String]) {
    println("Main")

    println("append")
    println(List.append(List(1,2,3), List(4,3,2,1)))

    println("tail")
    val a:List[Int] = List(1,2,3,4,5,6)
    val b = List.tail(a)
    List.tail(Nil)
    println(b)

    println("setHead")
    val c = List.setHead(a, 99)
    println(List.setHead(Nil, 99))

    println("drop")
    println(List.drop(Nil, 10))
    println(List.drop(c, 0))
    println(List.drop(c, 1))
    println(List.drop(c, 2))
    println(List.drop(c, 3))

    println("dropWhile")
    println(List.dropWhile(Nil, (x:Int) => x < 99))
    println(List.dropWhile(a, (x:Int) => x < 99))
    println(List.dropWhile(a, (x:Int) => x < 3))
    println(List.dropWhile(a, (x:Int) => x != 5))

    println("init")
    println(List.init(a))
    println(List.init(List(1,2,3)))
    println(List.init(List(1,2)))
  }
}
