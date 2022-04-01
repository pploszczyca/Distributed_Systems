package agh.edu.pl
package supplier

import models.{Equipment, Order}

import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

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

  private def generateNewNumberOfOrder(): String = {
    ordersCounter += 1
    s"${name}_${ordersCounter}"
  }
}
