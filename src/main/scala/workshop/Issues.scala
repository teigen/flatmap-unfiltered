package workshop

import unfiltered.filter.Plan
import unfiltered.request._
import unfiltered.response._

import dispatch._, Defaults._

import org.json4s._

import com.tristanhunt.knockoff.DefaultDiscounter._

case class Issues(user:String, password:String) extends Plan {

  implicit val formats = DefaultFormats

  def listIssues(i:List[Issue]) = Html(
    i.flatMap{ issue =>
      val href = s"/${issue.number}/comments"
      <a href={href}>{issue.title}</a><br/>
    })

  def listComments(c:List[Comment]) = Html(
    <ul>{ c.flatMap{ case Comment(body, User(login)) =>
      <h3>{login} :: </h3> ++ toXHTML(knockoff(body))
    }}</ul>
  )

  def issues = url("https://api.github.com/repos/unfiltered/unfiltered/issues").as(user, password)

  def intent = {
    case r@GET(Path("/")) =>
      val response = Http(issues OK as.json4s.Json)
      Ok ~> listIssues(response().extract[List[Issue]])

    case GET(Path(Seg(id :: "comments" :: Nil))) =>
      val comments = issues / id / "comments"
      val response = Http(comments OK as.json4s.Json)
      Ok ~> listComments(response().extract[List[Comment]])
  }
}

case class Issue(title:String, number:Int)
case class Comment(body:String, user:User)
case class User(login:String)
