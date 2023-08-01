@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package io.quickstart.mysql.infra

import io.quickstart.mysql.todos.entity.TodosTable
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.reactive.TransactionalOperator
import org.ufoss.kotysa.CoroutinesSqlClient
import org.ufoss.kotysa.mysql.MysqlTable
import org.ufoss.kotysa.spring.r2dbc.coSqlClient
import org.ufoss.kotysa.spring.r2dbc.transaction.SpringR2dbcCoroutinesTransactionalOp
import org.ufoss.kotysa.spring.r2dbc.transaction.coTransactionalOp
import org.ufoss.kotysa.tables

@Configuration
class DatabaseConfig(
    @Value("\${spring.r2dbc.url}")
    private val host: String,
    @Value("\${spring.r2dbc.username}")
    private val username: String,
    @Value("\${spring.r2dbc.password}")
    private val password: String
) : AbstractR2dbcConfiguration() {

    private val mysqlTables = arrayOf<MysqlTable<*>>(
        TodosTable
    )

    @Bean
    override fun connectionFactory(): ConnectionFactory =
        ConnectionFactories.get("r2dbcs:mysql://$username:$password@$host")

    @Bean
    fun sqlClient(dbClient: DatabaseClient): CoroutinesSqlClient =
        dbClient.coSqlClient(tables().mysql(*mysqlTables))

    @Bean
    fun operator(op: TransactionalOperator): SpringR2dbcCoroutinesTransactionalOp =
        op.coTransactionalOp()
}