package cl.uchile.dcc
package model.units

import model.units.UnitsCommon

trait EnemiesContract extends UnitsCommon {
  def attack: Int
}

class Enemy(val name: String, val health: Int, val attack: Int, val defense: Int, val weight: Int) extends EnemiesContract 
