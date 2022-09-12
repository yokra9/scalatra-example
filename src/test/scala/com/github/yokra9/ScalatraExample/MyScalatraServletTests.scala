package com.github.yokra9.ScalatraExample

import org.scalatra.test.scalatest._

class MyScalatraServletTests extends ScalatraFunSuite {

  addServlet(classOf[MyScalatraServlet], "/*")

  var step = 0
  test(s"${step}: ステータスコードが 200 であること") {
    get("/index") {
      status should equal(200)
    }
  }

  step += 1
  test(s"${step}: クエリパラメタが取得できていること") {
    get("/index", Map("q" -> "1"), Map("User-Agent" -> "scalatest")) {
      body should include("param: Map(q -> 1)")
    }
  }

  step += 1
  test(s"${step}: ユーザーエージェントが取得できていること") {
    get("/index", Map(), Map("User-Agent" -> "scalatest")) {
      body should include("user-agent: scalatest")
    }
  }

  step += 1
  test(s"${step}: 存在しないページのステータスコードが 404 であること") {
    get("/index2") {
      status should equal(404)
    }
  }

}
