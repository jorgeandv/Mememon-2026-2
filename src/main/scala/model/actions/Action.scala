package cl.uchile.dcc
package model.actions

import model.usable.usableCommon

trait ActionContract {
  def name: String
}

trait ActionWithUsable extends ActionContract {
  def objects: List[usableCommon]
}

class Attack(val name: String) extends ActionContract

class Move(val name: String) extends ActionContract

class EquipWeapon(val name: String, val objects: List[usableCommon]) extends ActionWithUsable

class ConsumePotion(val name: String, val objects: List[usableCommon]) extends ActionWithUsable

class Thunder(val name: String) extends ActionContract

class Meteor(val name: String) extends ActionContract

class Heal(val name: String) extends ActionContract

class Purify(val name: String) extends ActionContract