package cl.uchile.dcc
package model.panels

import model.units.{Character, Knight}

class PanelContractTest extends munit.FunSuite:
  var panel: Option[PanelContract] = None
  var knight: Option[Character] = None

  override def beforeEach(context: BeforeEach): Unit = {
    knight = Some(new Knight("Juan", 90, 10, 55, None, List("potion")))
    panel = Some(new Panel(1, 2 , List(knight.get)))
  }

  test("Panel fulfills the PanelContract"):
    val p = panel.get
    assertEquals(p.x, 1)
    assertEquals(p.y, 2)
    assertEquals(p.units, List(knight.get))


