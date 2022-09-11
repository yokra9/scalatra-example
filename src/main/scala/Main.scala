import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletHolder}
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object Main {
  def main(args: Array[String]): Unit = {
    start().join()
  }

  def start(): Server = {
    val context = new WebAppContext()
    context.setContextPath("/")
    context.setResourceBase("/src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    val port = sys.props.get("http.port").fold(8080) { _.toInt }
    val server = new Server(port)
    server.setHandler(context)
    server.start()

    server
  }
}
