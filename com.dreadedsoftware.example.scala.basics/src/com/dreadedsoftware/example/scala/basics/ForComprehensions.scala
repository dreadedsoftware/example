package com.dreadedsoftware.example.scala.basics

import scala.collection.mutable.ListBuffer

object ForComprehensions extends App{
  val list1: List[Int] = List.range(1, 101)
  val list2: List[Int] = List.range(-100, 0)
  
  val mutableList = scala.collection.mutable.ListBuffer[Int]()
  for(i <- list1){
    for(j <- list2){
      if(0 == i + j){
        mutableList += (i - j)
      }
    }
  }
  
  val collectionApiList =
    list1.
    flatMap{i =>
      list2.filter{j =>
        0 == i + j
      }.
      map{j =>
        i - j
      }
    }
  
  val comprehendedList =
    for{
      i <- list1
      j <- list2
      if 0 == i + j
    }yield{
      i - j
    }
  val comprehendedListBy4_1 =
    for{
      i <- list1 if 0 == i % 2
      j <- list2 if 0 == i + j
    }yield{
      i - j
    }
  val comprehendedListBy4_2 =
    for{
      i <- list1
      if 0 == i % 2
      j <- list2
      if 0 == i + j
    }yield{
      i - j
    }
  val comprehendedListBy4_3 =
    for{
      i <- list1
      j <- list2
      if 0 == i % 2
      if 0 == i + j
    }yield{
      i - j
    }
  val comprehendedListBy4_4 =
    for{
      i <- list1
      j <- list2
      if(0 == i % 2)
      if(0 == i + j)
    }yield{
      i - j
    }
  
}