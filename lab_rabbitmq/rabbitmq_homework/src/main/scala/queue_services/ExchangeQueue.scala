package agh.edu.pl
package queue_services

import com.rabbitmq.client.*

class ExchangeQueue(private val exchangeName: String,
                    private val exchangeType: BuiltinExchangeType,
                    private val key: String) extends ChannelService {

  override protected def initQueue(): Unit = {
    super.initQueue()
    channel.exchangeDeclare(exchangeName, exchangeType)
  }

  override def setUpToListen(defaultConsumer: DefaultConsumer): Unit = {
    val queueName = channel.queueDeclare.getQueue
    channel.queueBind(queueName, exchangeName, key)
    channel.basicConsume(queueName, true, defaultConsumer)
  }

  override def sendMessage(message: String): Unit = channel.basicPublish(exchangeName, key, null, message.getBytes)
}
