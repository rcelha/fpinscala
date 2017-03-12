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

    println("### bothMatch_2")
    println(Option bothMatch_2("T.*", ".*99", "Test 99"))
    println(Option bothMatch_2("Z.*", ".*99", "Test 99"))
    println(Option bothMatch_2("T.*", ".*666", "Test 99"))

    println("### sequence")
    println("->", Option.sequence(List(Some("oi"), Some("como vai vc"), Some("tchau"))))
    println("->", Option.sequence(List(opt1, Some("oi"), Some("como vai vc"), Some("tchau"))))
    println("->", Option.sequence(List(Some("oi"), Some("como vai vc"), Some("tchau"), opt1)))

    println("### traverse")
    def aux (x: String):Option[String] =
      if (x.length > 3) Some(x)
      else None

    println(Option.traverse(List("oie!", "como vai vc", "tchau"))(aux))
    println(Option.traverse(List("oi", "como vai vc", "tchau"))(aux))
    println(Option.traverse(List("oie!", "como vai vc", "bye"))(aux))

    println("### sequence2j")
    println(Option.sequence2(List(Some("oi"), Some("como vai vc"), Some("tchau"))))
    println(Option.sequence2(List(opt1, Some("oi"), Some("como vai vc"), Some("tchau"))))
    println(Option.sequence2(List(Some("oi"), Some("como vai vc"), Some("tchau"), opt1)))

    println("## Either")
    val l: Either[String, Integer] = Left("Value=123")
    val r: Either[String, Integer] = Right(123)
    println(l)
    println(r)

    println("### map")
    println(l.map(_ * 2))
    println(r.map(_ * 2))


    println("### flatMap")
    println(l.flatMap(x => Right(x * 2)))
    println(r.flatMap(x => Right(x * 2)))


    println("### orElse")
    println(l.orElse(Right(0)))
    println(l.orElse(Left("oi")))
    println(r.orElse(Right(0)))
    println(r.orElse(Left("oi")))

    println("### map2")
    println(l.map2(Right(999))(_ + _))
    println(r.map2(Right(999))(_ + _))

    println("### map2")
    println(Either.traverse(List(1,10,20,30,40,50))(x => if (x < 30) Right(x) else Left(s"The value is $x")))
    println(Either.traverse(List(1,10,20))(x => if (x < 30) Right(x) else Left(s"The value is $x")))

    println("### sequence")
    println(Either.sequence(List(r, Right(10), Right(20))))
    println(Either.sequence(List(r, Right(10), Right(20), l)))
  }
}
