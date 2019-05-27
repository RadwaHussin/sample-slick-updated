//package dao
//
//import com.exampleslick.models.definition.RoleId
//import models._
//import slick.jdbc.MySQLProfile.api._
//
//import scala.concurrent.Future
//
//class roledao extends basedao {
//
//  def findAll: Future[Seq[Rolex]] = db.run(rolesTable.result)
//  def create(role: Rolex): Future[RoleId] = rolesTable.returning(rolesTable.map(_.id)) += role
//  def findById(roleId: RoleId): Future[Rolex] = rolesTable.filter(_.id === roleId).result.head
//
//  def delete(roleId: RoleId): Future[Int] = rolesTable.filter(_.id === roleId).delete
//}
