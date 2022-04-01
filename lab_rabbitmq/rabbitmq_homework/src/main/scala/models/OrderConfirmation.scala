package agh.edu.pl
package models

import Constants.MESSAGE_SPLITTER

case class OrderConfirmation(numberOfOrder: String, order: Order) {
  override def toString: String = s"$numberOfOrder$MESSAGE_SPLITTER${order.toString}"
}
