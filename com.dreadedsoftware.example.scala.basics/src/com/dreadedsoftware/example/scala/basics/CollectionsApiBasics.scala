package com.dreadedsoftware.example.scala.basics

object CollectionsApiBasics extends App{
  val all1To10 = List.range(1, 11)
  val even2To20 =
    all1To10.
    map{item =>
      2 * item
    }
  
  val rangesOf10_1To100 =
    all1To10.
    map{item =>
      val base = (10 * (item - 1)) + 1
      List.range(base, base + 10)
    }
  val all1To100 =
    all1To10.
    flatMap{item =>
      val base = (10 * (item - 1)) + 1
      List.range(base, base + 10)
    }
  
  val even2To10 =
    all1To10.
    filter{item =>
      0 == item % 2
    }
}