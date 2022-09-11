package com.github.yokra9.ScalatraExample

import org.scalatra._
import org.slf4j.{Logger, LoggerFactory}

class MyScalatraServlet extends ScalatraServlet {

  val logger = LoggerFactory.getLogger(getClass)

  get("/index") {
    contentType = "text/plain"

    val str =
      s"param: ${params.toMap}, user-agent: ${request.getHeader("User-Agent")}"
    logger.info(str)

    Ok(str)
  }

}
