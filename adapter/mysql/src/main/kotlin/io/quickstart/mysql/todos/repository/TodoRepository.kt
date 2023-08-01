package io.quickstart.mysql.todos.repository

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.ports.driven.CreateTodoData
import io.quickstart.domain.todos.ports.driven.ReadingTodo
import io.quickstart.domain.todos.ports.driven.UpdateTodoData
import io.quickstart.domain.todos.ports.driven.WritingTodo
import io.quickstart.mysql.todos.entity.TodosTable
import io.quickstart.mysql.todos.entity.toDomain
import io.quickstart.mysql.todos.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Repository
import org.ufoss.kotysa.CoroutinesSqlClient
import org.ufoss.kotysa.spring.r2dbc.transaction.SpringR2dbcCoroutinesTransactionalOp


@Repository
class TodoRepository(
    private val sqlClient: CoroutinesSqlClient,
    private val operator: SpringR2dbcCoroutinesTransactionalOp,
) : ReadingTodo, WritingTodo {


    override suspend fun getById(id: Int): Todo? =
        (sqlClient selectFrom TodosTable
                where TodosTable.id eq id
                ).fetchOneOrNull()?.toDomain()

    override suspend fun getAll(): Flow<Todo> =
        (sqlClient selectAllFrom TodosTable)
            .map { it.toDomain() }

    override suspend fun create(data: CreateTodoData): Todo? {
        return operator.transactional {
            val inserted = sqlClient.insertAndReturn(data.toEntity())
            inserted.toDomain()
        }
    }

    override suspend fun update(id: Int, data: UpdateTodoData): Todo? {
        return operator.transactional {
            val updated = (
                    sqlClient update TodosTable
                            set TodosTable.title eq data.title
                            set TodosTable.description eq data.description
                            set TodosTable.doneAt eq data.doneAt
                            set TodosTable.doneBy eq data.doneBy
                            where TodosTable.id eq id
                    ).execute()

            if (updated.toInt() == 1) {
                getById(id)
            } else {
                null
            }
        }
    }
}