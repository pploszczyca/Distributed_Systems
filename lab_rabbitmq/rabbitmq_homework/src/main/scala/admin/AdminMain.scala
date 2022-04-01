package agh.edu.pl
package admin

import scala.io.StdIn.readLine

object AdminMain {
  def main(args: Array[String]): Unit = {
    val admin = Admin()

    while
      true
    do
      val input = readLine()
      admin.sendMessage(messageType = input(0), message = input.tail)
  }
}
