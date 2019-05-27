package dao

import models._  // 3shan satr da  val usersTable = TableQuery[UsersTable]
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}
import slick.jdbc.MySQLProfile.api._


import scala.concurrent.Future

class basedao  {


  val db = Database.forConfig("mysqlDB")


  val usersTable = TableQuery[UsersTable]
  val rolesTable = TableQuery[RolesTable]

  //Action must be a subtype of slick.dbio.Effect
  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    db.run(action)
  }

}

//so2al hena db. msh shayf el 7agat el mwgoda f main
