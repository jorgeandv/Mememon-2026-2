package cl.uchile.dcc
package model.combat

import model.units.{Archer, Character, EnemiesContract, Enemy, Knight, Thief, UnitsCommon}

import scala.collection.mutable.ArrayBuffer
import model.combat.{TurnScheduler, TurnSchedulerContract}
import model.usable.{Weapon, Sword}

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
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
    turnScheduler.get.add(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, archer))
    turnScheduler.get.add(thief)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, archer, thief))

  //test de los casos limite
  test("add an existing unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
  //--

  //Test del metodo remove -----
  //Test de las particiones
  test("remove with more than one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    val thief: Character = new Thief("Diego", 5, 5, 5, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(archer)
    turnScheduler.get.add(thief)
    turnScheduler.get.remove(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, thief))

  //test de los casos limite
  test("remove with one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.remove(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon]())

  test("remove with zero units"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.remove(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon]())

  test("remove a unit that is not in the scheduler"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.remove(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
//--

    //Test del metodo maxActionBarUnit -----
    //Test de las particiones
  test("maxActionBarUnit with an Character"):
    val sword: Weapon = new Sword("La BESTIA", 10,50, None)
    val knight: Character = new Knight("Juan", 10, 10, 10, Some(sword), Nil)
    assertEquals(turnScheduler.get.maxActionBarUnit(knight), 35.0)

  test("maxActionBarUnit with an Enemy"):
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    assertEquals(turnScheduler.get.maxActionBarUnit(enemy), 50.0)


    //test de los casos limite
  test("maxActionBarUnit without weapon"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    assertEquals(turnScheduler.get.maxActionBarUnit(knight), 10.0)
//--


  //Test del metodo reset -----
  //Test de las particiones
  test("reset sets the action bar of a unit back to 0"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 0)

  test("reset does not change the action bar of other units"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.actionBarsMap(enemy) = 12
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap.toMap, Map[UnitsCommon, Int](knight -> 0, enemy -> 12))

  //test de los casos limite
  test("reset a unit whose action bar is already 0 keeps it in 0"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 0)

  test("reset a unit that is not in the scheduler does not add it"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap.toMap, Map[UnitsCommon, Int]())

  test("reset a unit that is not in the scheduler does not change the others"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.reset(enemy)
    assertEquals(turnScheduler.get.actionBarsMap.toMap, Map[UnitsCommon, Int](knight -> 7))
//--