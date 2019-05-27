package models

import slick.jdbc.MySQLProfile.api._

case class Userx(id: Long, name: String)

case class Users(users: Seq[Userx])



  class UsersTable(tag: Tag) extends Table[Userx](tag, "User") {

    def id = column[Long]("UserId", O.PrimaryKey, O.AutoInc) // gowa el coulmn ba7ot no3 el id
    def name = column[String]("UserName")
    def * = (id, name) <> ((Userx.apply _).tupled, Userx.unapply) // userx hya nafs el table[userx]w nafs el case class userx w nafs seq
  }

