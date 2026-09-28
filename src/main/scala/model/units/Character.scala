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

class Thief(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: String, val utilities: List[String]) extends Character {
}

class BlackWizard(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: String, val utilities: List[String], val mana: Int) extends MagicCharacter {
}

class WhiteWizard(val name: String, val health: Int, val defense: Int, val weight:
Int, val weapon: String, val utilities: List[String], val mana: Int) extends MagicCharacter {
}

trait EnemiesContract {
  def name: String
  def health: Int
  def attack: Int
  def defense: Int
  def weight: Int
}

class Enemy(val name: String, val health: Int, val attack: Int, val defense: Int, val weight: Int) extends EnemiesContract {
}


trait Players {
  def units: List[String]
  def defeated(): Boolean
}

/*
class Player(val units: List[String]) extends Players{
  override def defeated(): Boolean = {
    It remains to be defined when it is considered defeated.
   }
}
 */

trait Weapon:
  def name: String
  def attack: Int
  def weight: Int
  def owner: Character

trait MagicWeapon extends Weapon:
  def magicAttack: Int

class Sword(val name: String, val attack: Int, val weight: Int,
            val owner: Character) extends Weapon

class Dagger(val name: String, val attack: Int, val weight: Int,
             val owner: Character) extends Weapon

class Bow(val name: String, val attack: Int, val weight: Int,
          val owner: Character) extends Weapon

class Wand(val name: String, val attack: Int, val weight: Int,
           val owner: Character, val magicAttack: Int) extends MagicWeapon

class Staff(val name: String, val attack: Int, val weight: Int,
            val owner: Character, val magicAttack: Int) extends MagicWeapon
