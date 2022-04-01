package agh.edu.pl
package models

case class Order(teamName: String, equipment: Equipment) {
  override def toString: String = s"${teamName}${Constants.MESSAGE_SPLITTER}${equipment.toString}"
}
