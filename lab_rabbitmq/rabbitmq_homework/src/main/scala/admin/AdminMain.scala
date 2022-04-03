package agh.edu.pl
package admin

import scala.io.StdIn.readLine

object AdminMain {
  def main(args: Array[String]): Unit = {
    val admin = Admin()

    println("1 - Send to Teams\n2 - Send to Supplier\n3 - Send to everyone")

    while
      true
    do
      val input = readLine()
      admin.sendMessage(messageType = input(0), message = input.tail)
  }
}
