package agh.edu.pl
package team

import Constants.{ADMIN_EXCHANGE, CONFIRM_ORDER_EXCHANGE, TEAM_FROM_ADMIN_KEY}
import models.{Equipment, Order}
import queue_services.{ExchangeQueue, SendOrderQueue}

import com.rabbitmq.client.{AMQP, BuiltinExchangeType, DefaultConsumer, Envelope}

import java.nio.charset.StandardCharsets

class Team(private val teamName: String) {
  private val sendOrderQueue = new SendOrderQueue()
  private val confirmOrderQueue = ExchangeQueue(CONFIRM_ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, teamName)

  DefaultPrinterConsumer.setPrinterToChannelService(confirmOrderQueue)
  setUpAdminMessagesListener()

  private def setUpAdminMessagesListener(): Unit =
    DefaultPrinterConsumer.setPrinterToChannelService(ExchangeQueue(ADMIN_EXCHANGE, BuiltinExchangeType.TOPIC, TEAM_FROM_ADMIN_KEY))

  def sendOrder(equipment: Equipment): Unit = sendOrderQueue.sendMessage(Order(teamName, equipment).toString)
}
