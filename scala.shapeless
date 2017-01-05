import shapeless._

object generic_derivation_test{
  trait Foo[Type]{
    def bar(t: Type): String
  }
  
  implicit def fooNil = new Foo[HNil]{def bar(t: HNil): String = ""}
  implicit def fooInt = new Foo[Int]{
    def bar(t: Int): String = t + ": Int"
  }
  implicit def fooString = new Foo[String]{
    def bar(t: String): String = t + ": String"
  }
  implicit def fooDouble = new Foo[Double]{
    def bar(t: Double): String = t + ": Double"
  }
  
  implicit def fooPrepend[Head, Tail<:HList](implicit
    head: Foo[Head],
    tail: Foo[Tail]): Foo[Head :: Tail] = new Foo[Head :: Tail]{
    def bar(t: Head :: Tail): String = {
      val hd :: tl = t
      head.bar(hd) + ", " + tail.bar(tl)
    }
  }
  
  implicit def fooProduct[P<:Product, H<:HList](implicit
    gen: Generic[P]{type Repr = H},
    foo: Foo[H]): Foo[P] = new Foo[P]{
    def bar(p: P): String = foo.bar(gen.to(p))
  }
  
  def testFooInstances(): Unit = {
    val testNil: Foo[HNil] = implicitly
    val testInt: Foo[Int] = implicitly
    val testString: Foo[String] = implicitly
    val testDouble: Foo[Double] = implicitly
    
    val a: Foo[Int :: HNil] = implicitly
    val b: Foo[String :: HNil] = implicitly
    val c: Foo[String :: Int :: HNil] = implicitly
    val d: Foo[Double :: String :: Int :: HNil] = implicitly
    val e: Foo[Double :: String :: Int :: String :: HNil] = implicitly
    
    val f: Foo[(String, Int)] = implicitly
    val g: Foo[(Double, String, Int)] = implicitly
    val h: Foo[(Double, String, Int, String)] = implicitly
    
    case class A(i1: Int, i2: Int, s: String)
    case class B(d: Double, s: String)
    case class C(i1: Int, d1: Double, s: String, d2: Double, i2: Int)
    val i: Foo[A] = implicitly
    val j: Foo[B] = implicitly
    val k: Foo[C] = implicitly
  }
}
