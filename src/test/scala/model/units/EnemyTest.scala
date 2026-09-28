package cl.uchile.dcc
package model.units

class EnemiesContractTest extends munit.FunSuite:
  var enemy: Option[EnemiesContract] = None

  override def beforeEach(context: BeforeEach): Unit = {
    enemy = Some(new Enemy("Jose", 90, 100, 55, 120))
  }

  test("Enemy fulfills the EnemiesContract contract"):
    val e = enemy.get
    assertEquals(e.name, "Jose")
    assertEquals(e.health, 90)
    assertEquals(e.attack, 100)
    assertEquals(e.defense, 55)
    assertEquals(e.weight, 120)
