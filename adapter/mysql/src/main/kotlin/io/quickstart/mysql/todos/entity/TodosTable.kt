package io.quickstart.mysql.todos.entity

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.ports.driven.CreateTodoData
import io.quickstart.domain.todos.ports.driven.UpdateTodoData
import org.ufoss.kotysa.mysql.MysqlTable
import java.time.LocalDateTime


data class TodoEntity(
    val id: Int? = null,
    val title: String,
    val description: String,
    val doneAt: LocalDateTime?,
    val doneBy: Int?
)

object TodosTable : MysqlTable<TodoEntity>("todos") {
    val id = autoIncrementInteger(TodoEntity::id).primaryKey()
    val title = varchar(TodoEntity::title)
    val description = varchar(TodoEntity::description)
    val doneAt = dateTime(TodoEntity::doneAt)
    val doneBy = integer(TodoEntity::doneBy)
}


fun TodoEntity.toDomain() = Todo(
    id = id!!,
    title = title,
    description = description,
    doneAt = doneAt,
    doneBy = doneBy
)


fun CreateTodoData.toEntity() = TodoEntity(
    title = title,
    description = description,
    doneAt = null,
    doneBy = null,
)

fun UpdateTodoData.toEntity(id: Int) = TodoEntity(
    id = id,
    title = title,
    description = description,
    doneAt = doneAt,
    doneBy = doneBy
)