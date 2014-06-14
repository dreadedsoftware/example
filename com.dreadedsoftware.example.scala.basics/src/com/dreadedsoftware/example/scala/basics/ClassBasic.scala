package com.dreadedsoftware.example.scala.basics

object ClassBasic{
  private val DEFAULT_NUMBER = 0
  private val DEFAULT_TEXT = ""
  
  //factory
  def apply(number: Int, text: String): ClassBasic =
    new ClassBasic(number, text)
  def apply(number: Int): ClassBasic =
    new ClassBasic(number)
  def apply(text: String): ClassBasic =
    new ClassBasic(text)
}
class ClassBasic(_number: Int, _text: String){
  def this(_text: String) = this(ClassBasic.DEFAULT_NUMBER, _text)
  def this(_number: Int) = this(_number, ClassBasic.DEFAULT_TEXT)
  
  private val number = _number
  private val text = _text
  
  private val concat = text + number
  
  def getNumber(): Int = number
  def getText(): String = text
  def getValue(): String = concat
  
  override def toString(): String = {
    "number = " + number + "\n" +
    "text = " + text + "\n" +
    "concat = " + concat
  }
  override def equals(any: Any): Boolean = {
    if(!any.isInstanceOf[ClassBasic]){
      false
    }
    else{
      val other = any.asInstanceOf[ClassBasic]
      (getNumber() equals other.getNumber()) &&
      (getText() equals other.getText())
    }
  }
  override def hashCode(): Int = concat.hashCode()
}
