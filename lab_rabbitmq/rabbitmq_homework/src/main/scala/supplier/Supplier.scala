package agh.edu.pl
package supplier

import models.{Equipment, Order, OrderConfirmation}

import agh.edu.pl.Constants.CONFIRM_ORDER_EXCHANGE
import com.rabbitmq.client.{AMQP, BuiltinExchangeType, DefaultConsumer, Envelope}

import java.nio.charset.StandardCharsets

class Supplier(private val name: String, private val ownedEquipmentList: List[Equipment]) {
  private var ordersCounter = 0
  private val sendOrderQueue = new SendOrderQueue()
  private val defaultConsumer = new DefaultConsumer(sendOrderQueue.channel) {
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit = {
      val message = String(body, StandardCharsets.UTF_8)

      val order = convertOrderMessage(message)

      if checkIfOrderCanBeAccept(order) then
        sendOrderQueue.acceptMessageDeliver(envelope)
        println(s"Accepted: $order")
        sendOrderConfirmation(order)
      else
        sendOrderQueue.rejectMessageDeliver(envelope)
    }
  }

  sendOrderQueue.setUpToListen(defaultConsumer)

  private def convertOrderMessage(message: String): Order = {
    val splittedMessage = message.split(Constants.MESSAGE_SPLITTER)
    Order(splittedMessage(0), Equipment.Oxygen.getByName(splittedMessage(1)))
  }

  private def checkIfOrderCanBeAccept(order: Order): Boolean = ownedEquipmentList.contains(order.equipment)

  private def sendOrderConfirmation(order: Order): Unit =
    ExchangeQueue(CONFIRM_ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, order.teamName)
      .sendMessage(makeOrderConfirmation(order).toString)

  private def makeOrderConfirmation(order: Order): OrderConfirmation = OrderConfirmation(generateNewNumberOfOrder(), order)

  private def generateNewNumberOfOrder(): String = {
    ordersCounter += 1
    s"${name}_${ordersCounter}"
  }
}
