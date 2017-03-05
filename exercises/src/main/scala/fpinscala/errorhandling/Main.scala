package fpinscala.errorhandling

object Main {
  def main (args: Array[String]) {
    println("# Error Handling")

    println("## Options")
    println(Some("oi"))

    println("### map")
    println(Some("map").map(x => x + " transformed"))
    val opt1: Option[String] = None
    println(opt1.map(x => x + " transformed"))

    println("### getOrElse")
    println(Some("map").getOrElse("------"))
    println(opt1.getOrElse("------"))

    println("### flatMap")
    println(Some("flat").flatMap(x => Some(x + " transformed")))
    println(opt1.flatMap(x => Some(x + " transformed")))

    println("### orElse")
    println(Some("orElse") orElse Some("orElse?"))
    println(opt1 orElse Some("orElse?"))

    println("### filter")
    val opt2: Option[Integer] = None
    println(Some(100) filter(_ > 10))
    println(Some(9) filter(_ > 10))
    println(opt2 filter(_ > 10))

    println("### variance")
    println(Option.variance(List(7, 6, 8, 4, 2, 7, 6, 7, 6, 5)))

    println("### map2")
    println(Option.map2(Some("Foo"), Some("Bla"))(_ + " " +  _))
    println(Option.map2(opt1, Some("Bla"))(_ + _))
    println(Option.map2(Some("Foo"), opt1)(_ + _))
    println(Option.map2(opt1, opt1)(_ + _))

    println("### bothMatch_1")
    println(Option bothMatch_2("T.*", ".*99", "Test 99"))
    println(Option bothMatch_2("Z.*", ".*99", "Test 99"))
    println(Option bothMatch_2("T.*", ".*666", "Test 99"))
  }
}
