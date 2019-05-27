import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import models._
import dao.userdao

import com.exampleslick.models.definition.UserId

import scala.util.{Failure, Success}

object UserRegistryActor {
  final case class ActionPerformed(action: String)
  final case object GetUsers
  final case class CreateUser(user: Userx)
  final case class GetUser(name: UserId)
  final case class DeleteUser(name: UserId)

  def props: Props = Props[UserRegistryActor]
}

class UserRegistryActor extends jasonSupport with Actor with ActorLogging {
  import UserRegistryActor._
  import context.dispatcher


  val userdao = new userdao()


  //DB Implementation here

  def receive: Receive = {
    case GetUsers =>
      //When sending a future to an actor, it is important to extract the sender first before sending.
      val mysender = sender
      val allUsers = userdao.findAll
      allUsers.onComplete {
        case Success(usr) => mysender ! Users(usr)
        case Failure(failureUsr) =>          println(failureUsr.getMessage)

          mysender ! Users(Vector.empty)

      }
    case CreateUser(user) =>
      userdao.create(user).onComplete({
        case Success(usr) => sender ! ActionPerformed(s"User ${usr} deleted")
        case Failure(failureUsr) => sender ! ActionPerformed(failureUsr.getMessage)
      })



    case GetUser(id) =>
      val user = userdao.findById(id)
      val userSender = sender
      user.onComplete {
        case Success(usr) => userSender ! usr
        case Failure(failureUsr) => userSender ! ActionPerformed(s"$id user not found")
      }
    case DeleteUser(id) =>
      val user = userdao.delete(id)
      val delSender = sender
      user.onComplete {
        case Success(del) => delSender ! ActionPerformed(s"User $id deleted")
        case Failure(delUser) => delSender ! ActionPerformed(s"Unable to Delete user $id")
      }
  }
}