
import UserRegistryActor._
import models._
//#json-support
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport  // 3shan 2adr 23ml extend
import spray.json.DefaultJsonProtocol


trait jasonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val userJsonFormat = jsonFormat2(Userx) //3shan a2dr 23ml import models
  implicit val usersJsonFormat = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
