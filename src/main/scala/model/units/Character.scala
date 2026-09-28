package cl.uchile.dcc
package model.units

trait Character {
  def name: String
  def health: Int
  def defense: Int
  def weight: Int
  def weapon: String
  def utilities: List[String]
}

trait MagicCharacter extends Character {
  def mana: Int
}


class Knight(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: String, val utilities: List[String]) extends Character {
}

class Archer(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: String, val utilities: List[String]) extends Character {
}