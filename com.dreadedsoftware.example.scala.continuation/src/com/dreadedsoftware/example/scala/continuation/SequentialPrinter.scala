package com.dreadedsoftware.example.scala.continuation

import scala.util.continuations._
import scala.collection.mutable.Buffer

object SequentialPrinter extends App{
  def divide = println("----------------------------------------------------------------")
  def continue0{
    println("entering reset")
    reset{
      println("before shift ")
      shift{cont: (Unit => Unit) =>
        println("start shift")
        cont()
        cont()
        cont()
        println("end shift")
      }
      println("inner")
    }
    println("exited reset")
  }
  def continue1{
    println("entering reset")
    reset{
      val curr =
        shift{cont: (Int => Int) =>
          println("start shift")
          cont(cont(cont(1)))
          println("end shift")
        }
      println("inner " + curr)
      curr + 1
    }
    println("exited reset")
  }
  def continue2(count: Int){
    println("entering reset")
    reset{
      val curr =
        shift{cont: (Int => Unit) =>
          println("start shift")
          1 to count foreach{num =>
            cont(num)
          }
          println
          println("end shift")
        }
      print(curr + ", ")
    }
    println("exited reset")
  }
  def continue3(count: Int){
    println("entering reset")
    val buffer: Buffer[Int] = Buffer()
    reset{
      val curr =
        shift{cont: (Int => Int) =>
          println("start shift")
          1 to count foreach{num =>
            cont(num)
          }
          println("end shift")
        }
      buffer += curr
      curr + 1
    }
    println(buffer)
    println("exited reset")
  }
  def continue4(countX: Int, countY: Int){
    println("entering reset")
    reset{
      val x =
        shift{cont: (Int => Unit) =>
          println("start shiftX")
          1 to countX foreach{num =>
            cont(num)
          }
          println("end shiftX")
        }
      println("tick X")
      val y =
        shift{cont: (Int => Unit) =>
          println("start shiftY")
          1 to countY foreach{num =>
            cont(num)
          }
          println("end shiftY")
        }
      
      println("x = " + x + ", y = " + y)
    }
    println("exited reset")
  }
  def continue5(countX: Int, countY: Int){
    println("entering reset")
    val map = scala.collection.mutable.Map[Int, Buffer[Int]]()
    reset{
      val x =
        shift{cont: (Int => Any) =>
          println("start shiftX")
          1 to countX foreach{num =>
            cont(num)
          }
          println("end shiftX")
        }
      val buffer: Buffer[Int] = Buffer()
      val tuple = (x, buffer)
      map += tuple
      val y =
        shift{cont: (Int => Any) =>
          println("start shiftY")
          1 to countY foreach{num =>
            cont(num)
          }
          println("end shiftY")
        }
      buffer += y
    }
    println(map)
    println("exited reset")
  }
//  def continue(count: Int){
//    println("entering reset")
//    reset{
//      shift{
//        println("start shift")
//        println("end shift")
//      }
//      println("inner)
//    }
//    println("exited reset")
//  }
  
  val amount = 10
  continue0

  divide
  continue1

  divide
  continue2(amount)

  divide
  continue3(amount)
  
  divide
  continue4(amount, amount)
  
  divide
  continue5(amount, amount)
}