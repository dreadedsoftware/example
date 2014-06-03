package com.dreadedsoftware.example.scala.basics

class FunctionsBasic {
  def foo(sum1: Int, sum2: Int, divisor: Int): Double={
    val sum = sum1 + sum2
    sum / divisor.toDouble
  }
  
  def plus3Over4AsNew(arg: Int): Double={
    val sum = arg + 3
    sum.toDouble / 4.0
  }
  
  def plus3Over4(arg: Int): Double = foo(arg, 3, 4)
  
  def addThenDivideBy4AsNew(sum1: Int, sum2: Int): Double={
    val sum = sum1 + sum2
    sum / 4.0
  }
  def addThenPowerOf4AsNew(sum1: Int, sum2: Int): Double={
    val sum = sum1 + sum2
    Math.pow(sum, 4)
  }
  
  def addThen(sum1: Int, sum2: Int, function: (Int => Double)): Double={
    val sum = sum1 + sum2
    function(sum)
  }
  private def divideBy4(arg: Int): Double ={
    arg / 4.0
  }
  private def powerOf4(arg: Int): Double={
    Math.pow(arg, 4)
  }
  def addThenDivideBy4(sum1: Int, sum2: Int) = addThen(sum1, sum2, divideBy4)
  def addThenPowerOf4(sum1: Int, sum2: Int) = addThen(sum1, sum2, powerOf4)
  
  def multiArgumentFunctionFunction(multiArgumentFunction: ((Int, Int, Double) => Double)): Double = 0.0
  
  def addThenDouble(sum1: Int, sum2: Int): Double =
    addThen(sum1, sum2,
      {sum =>
        sum * 2.0
      }
    )
  
  val fourArgs: ((Int, Int, Int, Int) => Int) = {(a, b, c, d) =>
    a + b + c + d
  }
  def manyArgs(
    a: Int,
    b: Int,
    c: Int,
    d: Int,
    e: Int,
    f: Int,
    g: Int,
    h: Int,
    i: Int,
    j: Int
  ): Int={
    val first = fourArgs(a, b, c, d)
    
    val threeArgs: ((Int, Int, Int) => Int) = fourArgs(_, _, _, 4)
    val second = threeArgs(e,f,g)
    
    val twoArgs: ((Int, Int) => Int) = threeArgs(_, _, 0)
    val third = twoArgs(h, i)
    
    val oneArg: (Int => Int) = twoArgs(_, 7)
    val fourth = oneArg(j)
    
    fourArgs(first, second, third, fourth)
  }
  
  def function(): Int={
    var variable = 1
    def innerFunction(arg: Int): Int={
      val increment = variable * 2
      arg + increment
    }
    variable = innerFunction(variable)
    innerFunction(variable)
  }
}