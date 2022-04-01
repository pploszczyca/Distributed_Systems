package agh.edu.pl
package admin

import Constants.*
import queue_services.ExchangeQueue

import com.rabbitmq.client.{AMQP, BuiltinExchangeType, DefaultConsumer, Envelope}

import java.nio.charset.StandardCharsets
import scala.io.StdIn.readLine

class Admin {
  private val orderExchange = ExchangeQueue(ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, ALL_KEY)
  private val confirmOrderQueue = ExchangeQueue(CONFIRM_ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, ALL_KEY)

  DefaultPrinterConsumer.setPrinterToChannelService(orderExchange)
  DefaultPrinterConsumer.setPrinterToChannelService(confirmOrderQueue)

  def sendMessage(messageType: Char, message: String): Unit =
    messageType match
      case '1' => sendMessage(message, ADMIN_TO_TEAM_KEY)
      case '2' => sendMessage(message, ADMIN_TO_SUPPLIER_KEY)
      case '3' => sendMessage(message, ADMIN_TO_EVERYONE_KEY)
      case _ => println("Try make message again")

  private def sendMessage(message: String, key: String): Unit =
    ExchangeQueue(ADMIN_EXCHANGE, BuiltinExchangeType.TOPIC, key).sendMessage(s"Admin:$message")

}
