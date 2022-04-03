package agh.edu.pl
package team

import models.Equipment
import scala.io.StdIn.readLine

object TeamMain {
  def main(args: Array[String]): Unit = {
    val team = Team("Team")
    val neededEquipment = List(Equipment.Oxygen, Equipment.Oxygen, Equipment.Boots, Equipment.Boots, Equipment.Backpack, Equipment.Backpack)

    for
      equipment <- neededEquipment
    do
      team.sendOrder(equipment)
  }
}

object TeamToWriteMain {
  def main(args: Array[String]): Unit = {
    print("Set team name: ")

    val team = Team(readLine())

    println()

    while
      true
    do
      team.sendOrder(Equipment.Oxygen.getByName(readLine()))
  }
}

