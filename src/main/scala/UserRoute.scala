


import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{ delete, get, post }
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import scala.concurrent.Future
import UserRegistryActor._
import akka.pattern.ask
import akka.util.Timeout
import models._




trait UserRoute extends jasonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[UserRoute])

  // other dependencies that UserRoutes use
  def userRegistryActor: ActorRef

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  def userRoutes: Route = pathPrefix("users") {
    pathPrefix("enroll-user") {
      pathEnd {
        concat(
          get {
            val users: Future[Users] =
              (userRegistryActor ? GetUsers).mapTo[Users]
            complete(users)
          },
          post {
            entity(as[Userx]) { user =>
              val userCreated = (userRegistryActor ? CreateUser(user)).mapTo[ActionPerformed]
              onSuccess(userCreated) { performed =>
                log.info(s"Created User [${user.name}]: ${performed.action}")
                complete(StatusCodes.Created, performed.action)
              }
            }
          })
      } ~ //Because we allow the DB to add the numbers hence id's are just 1,2,3 etc.
        path(IntNumber) { id =>
          get {
            val maybeUser: Future[Userx] = (userRegistryActor ? GetUser(id)).mapTo[Userx]
            rejectEmptyResponse {
              complete(maybeUser)
            }
          } ~
            delete {
              val userDeleted: Future[ActionPerformed] = (userRegistryActor ? DeleteUser(id)).mapTo[ActionPerformed]
              onSuccess(userDeleted) { performed =>
                log.info(s"Deleted user $id", performed.action)
                complete(StatusCodes.OK, performed)
              }
            }
        }
    }
  }


}
