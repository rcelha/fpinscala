package fpinscala.laziness

object Main {
  def main (args: Array[String]) {
    println("# Strictness and Laziness")

    val intStream = Stream(1,2,3,4,5,6)
    println(intStream)

    println("## toList")
    println(intStream.toList)

    println("## take")
    println((Empty: Stream[Integer]) take 0 toList)
    println(intStream take 0 toList)
    println(intStream take 1 toList)
    println(intStream take 3 toList)
    println(intStream take 5 toList)

    println("## takeWhile")
    println(intStream takeWhile (_ < 5) toList)

    println("## forAll")
    println(intStream forAll (_ < 10))
    println(intStream forAll (_ < 3))

    println("## foldedTakeWhile")
    println(intStream foldedTakeWhile (_ < 5) toList)
    println(intStream foldedTakeWhile (_ < 50) toList)

    println("## map")
    println(intStream map (x => s"R$$${x}") toList)

    println("## filter")
    println(intStream filter (_ % 2 == 0) toList)
    println(intStream filter (_ % 2 != 0) toList)

    println("## append")
    println(intStream append Stream(99,100) append Stream(101, 102) toList)

    println("## flatMap")
    println(intStream flatMap (x => Stream(x + 10, x + 100)) toList)
  }
}
