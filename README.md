# scalatra-example

## 使い方

```bash
# 起動
sbt ~jetty:start

# テストを実施
sbt test

# カバレッジを測定してレポートを出力 (target/scala-2.13/scoverage-report/)
sbt clean coverage test coverageReport

# Docker イメージのビルド
sbt docker:publishLocal

# Dockerfile 生成 (target/docker/stage)
sbt docker:stage

# 手動ビルドとコンテナの開始
docker build -t scalatra-example target/docker/stage/
docker run -p 8088:8088 --env JAVA_OPTS=-Dhttp.port=8088 scalatra-example
```