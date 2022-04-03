package agh.edu.pl
package queue_services

import models.Order

import agh.edu.pl.Constants
import com.rabbitmq.client.{BuiltinExchangeType, Channel, Connection, ConnectionFactory, DefaultConsumer, Envelope}
import Constants.{ORDER_EXCHANGE, ORDER_KEY}

class SendOrderQueue(private val queueName: String = Constants.ORDER_QUEUE)
  extends ExchangeQueue(ORDER_EXCHANGE, BuiltinExchangeType.TOPIC, ORDER_KEY) {

  override def setUpToListen(defaultConsumer: DefaultConsumer): Unit = {
    channel.queueDeclare(queueName, false, false, false, null)
    channel.queueBind(queueName, ORDER_EXCHANGE, ORDER_KEY)
    channel.basicQos(Constants.MAX_SAME_ITEM_AMOUNT)
    channel.basicConsume(queueName, false, defaultConsumer)
  }

  def acceptMessageDeliver(envelope: Envelope): Unit = channel.basicAck(envelope.getDeliveryTag, false)

  def rejectMessageDeliver(envelope: Envelope): Unit = channel.basicNack(envelope.getDeliveryTag, true, true)
}
