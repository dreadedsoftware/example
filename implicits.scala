package com.dreadedsoftware

object implicits extends App{
  import scala.language.implicitConversions
  
  //nice for post hoc polymorphism
  object conversions{
    implicit def intToString(int: Int): String = int.toString()
    def apply(): Unit = {
      assert(7 == numChars(1000000))
    }
    
    def numChars(string: String): Int = string.length()
  }
  
  //nice for ad-hoc polymorphism
  object arguments{
    implicit def lastName: String = "Stewart-Baxter"
    def apply(): Unit = {
      assert("Marjorie Stewart-Baxter" == name("Marjorie"))
    }
    
    def name(given: String)(implicit sur: String) = {
      s"$given $sur"
    }
  }
  
  //nice for extension decorating
  object wrappers{
    implicit class Namer(val str: String) extends AnyVal{
      def given: String = str
      def sur: String = "Stewart-Baxter"
      def full: String = s"$given $sur"
    }
    def apply(): Unit = {
      val name = "Marjorie"
      assert(name == name.given)
      assert("Stewart-Baxter" == name.sur)
      assert(s"$name Stewart-Baxter" == name.full)
    }
  }
  
  conversions()
  arguments()
  wrappers()
}
