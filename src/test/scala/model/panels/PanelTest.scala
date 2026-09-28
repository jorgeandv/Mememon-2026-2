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


class PanelTest extends munit.FunSuite:
  var center: Option[PanelContract] = None

  //Cada test parte sin ningún panel y luego con un panel en el centro (panel sin unidades para simplificar)
  override def beforeEach(context: BeforeEach): Unit = {
    center = Some(new Panel(0,0,Nil))
    PanelsList = Nil
  }

  //Test de las particiones
  test("nearbyPanels finds the four neighbors"):
    //Recordar que al hacer new Panel, automaticaemnte se guardan en PanelList
    val P1: PanelContract = new Panel(-1,0, Nil)
    val P2: PanelContract = new Panel(1,0, Nil)
    val P3: PanelContract = new Panel(0,-1, Nil)
    val P4: PanelContract = new Panel(0,1, Nil)
    assertEquals(center.get.nearbyPanels(), List(P1, P2, P3, P4))

  //Ojo,m el nerabyPanels va dejando como primero al ultimio panel que se ingresó
  test("nearbyPanels ignores far away panels"):
    val P1: PanelContract = new Panel(5, 5, Nil)
    val P2: PanelContract = new Panel(7, 0, Nil)
    assertEquals(center.get.nearbyPanels(), Nil)

  //Ya no usamos center
  test("nearbyPanels works with negative coordinates"):
    val P1: PanelContract = new Panel(-3, -4, Nil)
    val P2: PanelContract = new Panel(-3, -5, Nil)
    assertEquals(P1.nearbyPanels(), List(P2))
    assertEquals(P2.nearbyPanels(), List(P1))


  //test de los casos limite
  test("nearbyPanels ignores diagonal panels"):
    val P1: PanelContract = new Panel(-1, 1, Nil)
    val P2: PanelContract = new Panel(1, 1, Nil)
    val P3: PanelContract = new Panel(-1, -1, Nil)
    val P4: PanelContract = new Panel(1, -1, Nil)
    assertEquals(center.get.nearbyPanels(), Nil)

  test("nearbyPanels ignores a panel at the same position"):
    val P1: PanelContract = new Panel(0, 0, Nil)
    assertEquals(center.get.nearbyPanels(), Nil)



