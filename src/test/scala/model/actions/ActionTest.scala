package cl.uchile.dcc
package model.actions

class ActionContractTest extends munit.FunSuite:
  var attack: Option[ActionContract] = None
  var move: Option[ActionContract] = None
  var thunder: Option[ActionContract] = None
  var meteor: Option[ActionContract] = None
  var heal: Option[ActionContract] = None
  var purify: Option[ActionContract] = None

  override def beforeEach(context: BeforeEach): Unit = {
    attack = Some(new Attack("FAA"))
    move = Some(new Move("Right"))
    thunder = Some(new Thunder("Thor"))
    meteor = Some(new Meteor("Dead"))
    heal = Some(new Heal("+1"))
    purify = Some(new Purify("lol"))
  }

  test("Attack fulfills the ActionContract"):
    val a = attack.get
    assertEquals(a.name, "FAA")

  test("Move fulfills the ActionContract"):
    val m = move.get
    assertEquals(m.name, "Right")

  test("Thunder fulfills the ActionContract"):
    val t = thunder.get
    assertEquals(t.name, "Thor")

  test("Meteor fulfills the ActionContract"):
    val mm = meteor.get
    assertEquals(mm.name, "Dead")

  test("Heal fulfills the ActionContract"):
    val h = heal.get
    assertEquals(h.name, "+1")

  test("Purify fulfills the ActionContract"):
    val p = purify.get
    assertEquals(p.name, "lol")


class ActionWithUsableTest extends munit.FunSuite:
  var equipWeapon: Option[ActionWithUsable] = None
  var consumePotion: Option[ActionWithUsable] = None

  override def beforeEach(context: BeforeEach): Unit = {
    equipWeapon = Some(new EquipWeapon("Knife", Nil))
    consumePotion = Some(new ConsumePotion("Fortitude", Nil))
  }

  test("EquipWeapon fulfills the ActionWithUsable"):
    val e = equipWeapon.get
    assertEquals(e.name, "Knife")
    assertEquals(e.objects, Nil)

  test("ConsumePotion fulfills the ActionWithUsable"):
    val c = consumePotion.get
    assertEquals(c.name, "Fortitude")
    assertEquals(c.objects, Nil)


