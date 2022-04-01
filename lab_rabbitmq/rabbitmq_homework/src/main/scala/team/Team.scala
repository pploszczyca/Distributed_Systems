package agh.edu.pl
package team

import models.{Equipment, Order}

import agh.edu.pl.Constants.CONFIRM_ORDER_EXCHANGE
import agh.edu.pl.ExchangeQueue
import com.rabbitmq.client.{AMQP, BuiltinExchangeType, DefaultConsumer, Envelope}

import java.nio.charset.StandardCharsets

class Team(private val teamName: String) {
  private val sendOrderQueue = new SendOrderQueue()
  private val confirmOrderQueue = ExchangeQueue(CONFIRM_ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, teamName)

  private val defaultConsumer = new DefaultConsumer(sendOrderQueue.channel) {
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
      val message = String(body, StandardCharsets.UTF_8)
      println(message)
    }
  }
  
  confirmOrderQueue.setUpToListen(defaultConsumer)

  def sendOrder(equipment: Equipment): Unit = sendOrderQueue.sendOrder(Order(teamName, equipment))

}
