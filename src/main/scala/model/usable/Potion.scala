package cl.uchile.dcc
package model.usable

trait usableCommon {
  def name: String
}

trait Potion extends usableCommon

class HealingPotion(val name: String) extends Potion

class FortitudePotion(val name: String) extends Potion

class ManaPotion(val name: String) extends Potion

class MagicStrengthPotion(val name: String) extends Potion