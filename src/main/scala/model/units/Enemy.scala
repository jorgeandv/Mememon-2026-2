package cl.uchile.dcc
package model.units

trait EnemiesContract extends UnitsCommon {
  def attack: Int
}

class Enemy(val name: String, val health: Int, val attack: Int, val defense: Int, val weight: Int) extends EnemiesContract 
