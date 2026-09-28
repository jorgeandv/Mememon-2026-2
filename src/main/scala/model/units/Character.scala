package cl.uchile.dcc
package model.units

import model.usable.Weapon

trait Character {
  def name: String
  def health: Int
  def defense: Int
  def weight: Int
  def weapon: Option[Weapon]
  def utilities: List[String]
}

trait MagicCharacter extends Character {
  def mana: Int
}


class Knight(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: Option[Weapon], val utilities: List[String]) extends Character


class Archer(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: Option[Weapon], val utilities: List[String]) extends Character


class Thief(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: Option[Weapon], val utilities: List[String]) extends Character


class BlackWizard(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: Option[Weapon], val utilities: List[String], val mana: Int) extends MagicCharacter


class WhiteWizard(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: Option[Weapon], val utilities: List[String], val mana: Int) extends MagicCharacter








