package cl.uchile.dcc
package model.usable

import model.units.Character
import model.usable.usableCommon

trait Weapon extends usableCommon:
  def attack: Int
  def weight: Int
  def owner: Option[Character]

trait MagicWeapon extends Weapon:
  def magicAttack: Int

class Sword(val name: String, val attack: Int, val weight: Int,
            val owner: Option[Character]) extends Weapon

class Dagger(val name: String, val attack: Int, val weight: Int,
             val owner: Option[Character]) extends Weapon

class Bow(val name: String, val attack: Int, val weight: Int,
          val owner: Option[Character]) extends Weapon

class Wand(val name: String, val attack: Int, val weight: Int,
           val owner: Option[Character], val magicAttack: Int) extends MagicWeapon

class Staff(val name: String, val attack: Int, val weight: Int,
            val owner: Option[Character], val magicAttack: Int) extends MagicWeapon

