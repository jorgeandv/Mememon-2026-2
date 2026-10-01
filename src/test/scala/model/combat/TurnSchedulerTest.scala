package cl.uchile.dcc
package model.panels

import model.units.{Character, Knight, Archer, Thief}
import scala.collection.mutable.ArrayBuffer

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
    val arrayBufferTest = turnScheduler.get.add(knight, archer, thief)
    assertEquals(arrayBufferTest, ArrayBuffer(knight, archer, thief) )


  //test de los casos limite
  test("add with zero units"):
    val arrayBufferTest = turnScheduler.get.add()
    assertEquals(arrayBufferTest, ArrayBuffer[UnitsCommon]())
  //--