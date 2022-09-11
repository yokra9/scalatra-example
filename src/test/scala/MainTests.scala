import cats.effect.IO
import cats.effect.unsafe.IORuntime
import java.util.concurrent.Executors
import org.http4s.client._
import org.http4s.ember.client._
import org.scalatest.funsuite.AnyFunSuite

class MainTests extends AnyFunSuite {
  implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

  var step = 0
  test(s"${step}: サーバが起動すること") {
    val server = Main.start()

    val httpClient: Client[IO] = JavaNetClientBuilder[IO].create
    val req = httpClient.expect[String]("http://localhost:8080/index")
    val res = req.unsafeRunSync()
    assert(res.contains("param: Map()"))

    server.stop()
  }

  step += 1
  test(s"${step}: 環境変数でポート指定ができること") {
    sys.props("http.port") = "8082"
    val server = Main.start()

    val httpClient: Client[IO] = JavaNetClientBuilder[IO].create
    val req = httpClient.expect[String]("http://localhost:8082/index")
    val res = req.unsafeRunSync()
    assert(res.contains("param: Map()"))

    server.stop()
  }
}
