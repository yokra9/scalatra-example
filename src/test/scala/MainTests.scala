import cats.effect.IO
import cats.effect.unsafe.IORuntime
import java.util.concurrent.Executors
import org.http4s.client._
import org.http4s.ember.client._
import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers._
import org.scalatest.prop._
import scala.collection.immutable._

class MainTests extends AnyFunSuite with should.Matchers {
  implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

  var step = 0
  test(s"${step}: サーバが起動すること") {
    val server = Main.start()
    val httpClient: Client[IO] = JavaNetClientBuilder[IO].create
    val req = httpClient.expect[String]("http://localhost:8080/index")
    val res = req.unsafeRunSync()
    server.stop()

    res should include("param: Map()")
  }

  step += 1
  test(s"${step}: システムプロパティでポート指定ができること") {
    sys.props("http.port") = "8082"
    val server = Main.start()
    val httpClient: Client[IO] = JavaNetClientBuilder[IO].create
    val req = httpClient.expect[String]("http://localhost:8082/index")
    val res = req.unsafeRunSync()
    server.stop()

    res should include("param: Map()")
  }

  step += 1
  test(s"${step}: システムプロパティで異常値が設定されていても 8080 にフォールバックされること") {
    sys.props("http.port") = "Int に変換できない値"
    val server = Main.start()
    val httpClient: Client[IO] = JavaNetClientBuilder[IO].create
    val req = httpClient.expect[String]("http://localhost:8080/index")
    val res = req.unsafeRunSync()
    server.stop()

    res should include("param: Map()")
  }
}
