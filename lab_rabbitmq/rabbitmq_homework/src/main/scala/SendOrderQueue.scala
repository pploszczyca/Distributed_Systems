package agh.edu.pl

import models.Order

import agh.edu.pl.Constants
import com.rabbitmq.client.{Channel, Connection, ConnectionFactory, DefaultConsumer, Envelope}

class SendOrderQueue(private val queueName: String = Constants.ORDER_QUEUE) {
  var channel: Channel = null
  private var connection: Connection = null

  initQueue()

  private def initQueue(): Unit = {
    val factory = new ConnectionFactory()
    factory.setHost(Constants.HOST)
    connection = factory.newConnection()
    channel = connection.createChannel()
    channel.queueDeclare(queueName, false, false, false, null)
  }

  def setUpToListen(defaultConsumer: DefaultConsumer): Unit = {
    channel.basicQos(Constants.MAX_SAME_ITEM_AMOUNT)
    channel.basicConsume(queueName, false, defaultConsumer)
  }

  def sendOrder(order: Order): Unit = channel.basicPublish("", queueName, null, s"${order.teamName}${Constants.MESSAGE_SPLITTER}${order.equipment.toString}".getBytes)

  def acceptMessageDeliver(envelope: Envelope): Unit = channel.basicAck(envelope.getDeliveryTag, false)

  def rejectMessageDeliver(envelope: Envelope): Unit = channel.basicNack(envelope.getDeliveryTag, true, true)

  def closeConnection(): Unit = {
    channel.close()
    connection.close()
  }
}
