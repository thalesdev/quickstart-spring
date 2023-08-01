rootProject.name = "quickstart-spring"

include(
    "main",
    "http-server",
    "consumer",
    "domain",
    "mysql",
    "kafka"
)

project(":http-server").projectDir = file("apps/http-server")
project(":consumer").projectDir = file("apps/consumer")
project(":mysql").projectDir = file("adapter/mysql")
project(":kafka").projectDir = file("adapter/kafka")