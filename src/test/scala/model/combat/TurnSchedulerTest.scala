package cl.uchile.dcc
package model.combat

import model.units.{UnitsCommon, Character, Knight, Archer, Thief}
import scala.collection.mutable.ArrayBuffer
import model.combat.{TurnSchedulerContract, TurnScheduler}

class TurnSchedulerTest extends munit.FunSuite:
  var turnScheduler: Option[TurnSchedulerContract] = None

  override def beforeEach(context: BeforeEach): Unit = {
    turnScheduler = Some(new TurnScheduler)
  }

  //Test del metodo add -----
  //Test de las particiones
  test("add with more than one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    val thief: Character = new Thief("Diego", 5, 5, 5, None, Nil)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer(knight))
    turnScheduler.get.add(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer(knight, archer))
    turnScheduler.get.add(thief)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer(knight, archer, thief))

  //test de los casos limite
  test("add an existing unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer(knight))
  //--