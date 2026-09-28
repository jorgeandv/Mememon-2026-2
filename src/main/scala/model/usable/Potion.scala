package cl.uchile.dcc
package model.usable

import model.units.Potion

trait Potion:
  def name: String

class HealingPotion(val name: String) extends Potion

class FortitudePotion(val name: String) extends Potion

class ManaPotion(val name: String) extends Potion

class MagicStrengthPotion(val name: String) extends Potion