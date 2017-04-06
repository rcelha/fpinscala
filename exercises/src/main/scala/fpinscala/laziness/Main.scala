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

    println("## ones")
    println(Stream.ones take 10 toList)

    println("## contant")
    println(Stream.constant(6) take 3 toList)

    println("## from")
    println(Stream.from(6) take 10 toList)

    println("## fibs")
    println(Stream.fibs take 10 toList)

    println("## unfold")
    println(Stream.unfold (0)(x => if (x > 10) None else Some(x + 1, x + 1)) toList)

    println("## unfold - fibs")
    println(Stream.fibsByUnfold take 10 toList)

    println("## unfold - from")
    println(Stream.fromByUnfold(6) take 10 toList)

    println("## unfold - contant")
    println(Stream.constantByUnfold(6) take 3 toList)

    println("## unfold - map")
    println(intStream mapByUnfold (x => s"R$$${x}") toList)

    println("## unfold - take")
    println((Empty: Stream[Integer]) takeByUnfold 0 toList)
    println(intStream takeByUnfold 0 toList)
    println(intStream takeByUnfold 1 toList)
    println(intStream takeByUnfold 3 toList)
    println(intStream takeByUnfold 5 toList)

    println("## unfold - takeWhile")
    println(intStream takeWhileByUnfold (_ < 3) toList)
    println(intStream takeWhileByUnfold (_ < 50) toList)

    println("## unfold - zip")
    println(intStream zip Stream(11,12,13,14,15,16) toList)
    println(intStream zip Stream(11,12,13,14) toList)

    println("## unfold - zipAll")
    println(intStream zipAll(Stream(11,12,13,14,15,16), 666, 999) toList)
    println(intStream zipAll(Stream(11,12,13,14), 666, 999) toList)
    println(intStream zipAll(Stream(11,12,13,14,15,16,17,18), 666, 999) toList)
    println(Stream(1,2,3) zipAll(Stream(1,2), 0, 0) toList)

    println("## startsWith")
    println(Stream(1,2,3) startsWith Stream(1,2))
    println(Stream(1,2,3) startsWith Stream(1,2,3,4))
    println(Stream(1,2,4,5,6) startsWith Stream(1,2,3,4))
    println(Stream(1,2,4,5,6) startsWith Stream(1,2,3))
  }
}
