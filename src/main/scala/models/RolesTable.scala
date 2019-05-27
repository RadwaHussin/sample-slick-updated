package models

import slick.jdbc.MySQLProfile.api._

case class Rolex(id: Long, name: String)

case class Roles(roles: Seq[Rolex])


class RolesTable (tag: Tag) extends Table[Rolex](tag, "Role") {

  def id = column[Long]("RoleId", O.PrimaryKey, O.AutoInc) // gowa el coulmn ba7ot no3 el id
  def name = column[String]("RoleName")
  def * = (id, name) <> ((Rolex.apply _).tupled, Rolex.unapply) // rolex hya nafs el table[rolex]w nafs el case class rolex w nafs seq
}
