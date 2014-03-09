package com.dreadedsoftware.example.scala.continuation

import scala.util.continuations._

object ContinuationMath extends App{
  def triangle(count: Long): Long={
    var accumulator = 0L
    reset{
      val curr =
        shift{cont: (Long => Long) =>
          var curr = cont(count)
          while(0 < curr){
            curr = cont(curr)
          }
        }
      accumulator += curr
      curr - 1
    }
    accumulator
  }
  
  def fib(count: Long): Long={
    if(0 == count) return 0
    else if(1 == count) return 1
    var last = 0L
    var accumulator = 1L
    reset{
      val curr =
        shift{cont: (Unit => Long) =>
          2L to count foreach{num =>
            accumulator = cont()
          }
        }
      val result = accumulator + last
      last = accumulator
      result
    }
    accumulator
  }
  
  def pow(base: Long, exponent: Long): Long={
    var accumulator = 1L
    reset{
      shift{cont: (Unit => Unit) =>
        1L to exponent foreach{num =>
          cont()
        }
      }
      accumulator *= base
    }
    accumulator
  }
  
  def fact(count: Long): Long={
    if(2 > count) return 1
    var accumulator = 1L
    reset{
      val curr =
        shift{cont: (Long => Long) =>
          var curr = cont(count)
          while(1 < curr){
            curr = cont(curr)
          }
        }
      accumulator *= curr
      curr - 1
    }
    accumulator
  }
  
  //uses ramanujan series
  def inversePi(terms: Long): Double={
    def divide(top: String, bot: String){
      println("  " + top)
      println("  ____________")
      println("  " + bot)
    }
    val constant = 2.0 * Math.sqrt(2) / 9801.0
    var accumulator = 0.0
    reset{
      val k =
        shift{cont: (Long => Unit) =>
          0L until terms foreach{num=>
            cont(num)
          }
        }
      val topLeft = fact(4 * k) toDouble
      val topRight = (1103 + 26390 * k) toDouble
      val botLeft = pow(fact(k), 4) toDouble
      val botRight = pow(396, 4 * k) toDouble
      
      val top = topLeft * topRight
      val bot = botLeft * botRight
      
      val term = top / bot
      accumulator += term
    }
    constant * accumulator
  }
  
  val amount1 = 8L
  val amount2 = 4L
  println(triangle(amount1))
  println(fib(amount1))
  println(fact(amount1))
  println(pow(amount1, amount2))
  val invPi = inversePi(amount1)
  println(1.0 / invPi)
}