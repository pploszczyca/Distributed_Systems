package agh.edu.pl
package queue_services

import models.Order

import agh.edu.pl.Constants
import com.rabbitmq.client.{Channel, Connection, ConnectionFactory, DefaultConsumer, Envelope}

class SendOrderQueue(private val queueName: String = Constants.ORDER_QUEUE) extends ChannelService {
  override protected def initQueue(): Unit = {
    super.initQueue()
    channel.queueDeclare(queueName, false, false, false, null)
  }

  def sendMessage(order: Order): Unit = sendMessage(order.toString)

  override def sendMessage(message: String): Unit = channel.basicPublish("", queueName, null, message.getBytes)

  override def setUpToListen(defaultConsumer: DefaultConsumer): Unit = {
    channel.basicQos(Constants.MAX_SAME_ITEM_AMOUNT)
    channel.basicConsume(queueName, false, defaultConsumer)
  }

  def acceptMessageDeliver(envelope: Envelope): Unit = channel.basicAck(envelope.getDeliveryTag, false)

  def rejectMessageDeliver(envelope: Envelope): Unit = channel.basicNack(envelope.getDeliveryTag, true, true)

  def closeConnection(): Unit = {
    channel.close()
    connection.close()
  }
}
