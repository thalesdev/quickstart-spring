package io.quickstart.kafka.todos

import io.quickstart.domain.todos.entities.TodoEvent
import io.quickstart.domain.todos.ports.driven.NotifyingTodo
import io.quickstart.kafka.infra.Topics
import io.quickstart.kafka.todos.messages.toMessage
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TodoPublisher(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : NotifyingTodo {

    private val logger = LoggerFactory.getLogger(javaClass)

    override suspend fun notify(todo: TodoEvent) {
        logger.info("Publishing todo event: $todo")
        kafkaTemplate.send(Topics.TP_TODOS, todo.toMessage())
    }
}