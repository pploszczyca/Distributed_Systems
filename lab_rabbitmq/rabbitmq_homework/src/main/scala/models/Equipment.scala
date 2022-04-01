package agh.edu.pl
package models

enum Equipment:
  case Oxygen, Boots, Backpack

  def getByName(lookingEquipmentName: String): Equipment = {
    for
      equipment <- Equipment.values
    do
      if equipment.toString == lookingEquipmentName then
        return equipment

    Equipment.Oxygen
  }
