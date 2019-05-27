package dao

import models._
import com.exampleslick.models.definition.UserId
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class userdao extends basedao {

  def findAll: Future[Seq[Userx]] = db.run(usersTable.result)
  def create(user: Userx): Future[UserId] = usersTable.returning(usersTable.map(_.id)) += user
  def findById(userId: UserId): Future[Userx] = usersTable.filter(_.id === userId).result.head

  def delete(userId: UserId): Future[Int] = usersTable.filter(_.id === userId).delete
}
