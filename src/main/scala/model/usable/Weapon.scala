package cl.uchile.dcc
package model.usable

import model.units.Character

trait Weapon extends usableCommon {
  def attack: Int
  def weight: Int
  def owner: Option[Character]
}

trait MagicWeapon extends Weapon {
  def magicAttack: Int
}


abstract class AbstractWeapon(val name: String, val attack: Int, val weight: Int, val owner: Option[Character]) extends Weapon

abstract class AbstractMagicWeapon(name: String, attack: Int, weight: Int, owner: Option[Character], val magicAttack: Int)
  extends AbstractWeapon(name, attack, weight, owner) with MagicWeapon

class Sword(name: String, attack: Int, weight: Int,
            owner: Option[Character]) extends AbstractWeapon(name, attack, weight, owner)

class Dagger(name: String, attack: Int, weight: Int,
             owner: Option[Character]) extends AbstractWeapon(name, attack, weight, owner)

class Bow(name: String, attack: Int, weight: Int,
          owner: Option[Character]) extends AbstractWeapon(name, attack, weight, owner)

class Wand(name: String, attack: Int, weight: Int,
           owner: Option[Character], magicAttack: Int) extends AbstractMagicWeapon(name, attack, weight, owner, magicAttack)

class Staff(name: String, attack: Int, weight: Int,
            owner: Option[Character], magicAttack: Int) extends AbstractMagicWeapon(name, attack, weight, owner, magicAttack)

