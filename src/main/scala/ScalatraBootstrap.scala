import com.github.yokra9.ScalatraExample._
import javax.servlet.ServletContext
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    context.mount(new MyScalatraServlet, "/*")
  }
}
