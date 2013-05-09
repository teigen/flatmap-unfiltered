scalaVersion := "2.10.1"

organization := "flatmap"

name := "unfiltered-workshop"

version := "0.1-SNAPSHOT"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-core" % "0.10.0"

libraryDependencies += "net.databinder.dispatch" %% "dispatch-json4s-native" % "0.10.0"

libraryDependencies += "net.databinder" %% "unfiltered-filter" % "0.6.8"

libraryDependencies += "net.databinder" %% "unfiltered-jetty" % "0.6.8"

libraryDependencies += "com.tristanhunt" %% "knockoff" % "0.8.1"