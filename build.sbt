name := "sample-slick"

version := "0.1"

scalaVersion := "2.12.8"

val mySqlVersion = "5.1.34"

libraryDependencies ++= Seq(
  //Akka packages
  "com.typesafe.akka"        %% "akka-actor"             % "2.5.13",
  "com.typesafe.akka"        %% "akka-stream"            % "2.5.13",
  "com.typesafe.akka"        %% "akka-http"              % "10.1.3",
  "com.typesafe.akka"        %% "akka-http-spray-json"   % "10.1.3",

  //Database packages "slick"
  "com.typesafe.slick"       %% "slick"                  % "3.3.0",
  "com.typesafe.slick"       %% "slick-hikaricp"         % "3.2.3",
  "mysql"                    % "mysql-connector-java"    % mySqlVersion ,

  "org.slf4j"                % "slf4j-nop"               % "1.6.4",
  "com.microsoft.sqlserver"  % "mssql-jdbc"              % "6.2.1.jre8",
)