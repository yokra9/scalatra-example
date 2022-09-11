package com.github.yokra9.ScalatraExample

import org.scalatra.test.scalatest._

class MyScalatraServletTests extends ScalatraFunSuite {

  addServlet(classOf[MyScalatraServlet], "/*")

  var step = 0
  test(s"${step}: ステータスコードが200であること") {
    get("/index") {
      status should equal(200)
    }
  }

  step += 1
  test(s"${step}: クエリパラメタを確認") {
    get("/index", Map("q" -> "1"), Map("User-Agent" -> "scalatest")) {
      body should include("param: Map(q -> 1)")
    }
  }

  step += 1
  test(s"${step}: ユーザーエージェントを確認") {
    get("/index", Map(), Map("User-Agent" -> "scalatest")) {
      body should include("user-agent: scalatest")
    }
  }

  step += 1
  test(s"${step}: 存在しないページのステータスコードが404であること") {
    get("/index2") {
      status should equal(404)
    }
  }

}
