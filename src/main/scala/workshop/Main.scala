package workshop

import unfiltered.jetty.Http

object Main extends App {
  val issues = Issues(args(0), args(1))
  Http(8080).plan(issues).run()
}
