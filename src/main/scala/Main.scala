import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletContextHandler}
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener
import org.slf4j.{Logger, LoggerFactory}

object Main {
  val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    start().join()
  }

  def start(): Server = {
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setResourceBase("/src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    val port = sys.props.getOrElse("http.port", "8080").toIntOption match {
      case Some(port) => port
      case None => {
        logger.error("システムプロパティ http.port には整数値を指定してください")
        8080
      }
    }

    val server = new Server(port)
    server.setHandler(context)
    server.start()

    server
  }
}
