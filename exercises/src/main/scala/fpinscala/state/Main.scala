package fpinscala.state

object Main {
  def main (args: Array[String]) {
    println("State")

    val foo = new RNG.Simple(10)
    val (val1, rnd1) = foo.nextInt
    val (val2, rnd2) = rnd1.nextInt
    val (val3, rnd3) = rnd2.nextInt
    val (val4, rnd4) = rnd3.nextInt
    val (val5, rnd5) = rnd4.nextInt

    println(val1)
    println(val2)
    println(val3)
    println(val4)
    println(val5)

    println("## nonNegativeInt")
    println(RNG.nonNegativeInt(rnd1))
    println(RNG.nonNegativeInt(rnd2))
    println(RNG.nonNegativeInt(rnd3))
    println(RNG.nonNegativeInt(rnd4))
    println(RNG.nonNegativeInt(rnd5))

    println("## double")
    println(RNG.double(rnd1))
    println(RNG.double(rnd2))
    println(RNG.double(rnd3))
    println(RNG.double(rnd4))
    println(RNG.double(rnd5))

    println("## intDouble")
    println(RNG.intDouble(rnd1))
    println(RNG.intDouble(rnd2))
    println(RNG.intDouble(rnd3))
    println(RNG.intDouble(rnd4))
    println(RNG.intDouble(rnd5))

    println("## doubleInt")
    println(RNG.doubleInt(rnd1))
    println(RNG.doubleInt(rnd2))
    println(RNG.doubleInt(rnd3))
    println(RNG.doubleInt(rnd4))
    println(RNG.doubleInt(rnd5))

    println("## double3")
    println(RNG.double3(rnd1))
    println(RNG.double3(rnd2))
    println(RNG.double3(rnd3))

    println("## ints")
    println(RNG.ints(5)(foo))

    println("## doubleViaMap")
    println(RNG.doubleViaMap(rnd1))
    println(RNG.doubleViaMap(rnd2))
    println(RNG.doubleViaMap(rnd3))
    println(RNG.doubleViaMap(rnd4))
    println(RNG.doubleViaMap(rnd5))

    println("## map2")
  }
}
