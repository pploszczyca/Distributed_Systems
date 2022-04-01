package agh.edu.pl
package team

import models.{Equipment, Order}

class Team(private val teamName: String) {
  private val sendOrderQueue = new SendOrderQueue()

  def sendOrder(equipment: Equipment): Unit = sendOrderQueue.sendOrder(Order(teamName, equipment))
}
