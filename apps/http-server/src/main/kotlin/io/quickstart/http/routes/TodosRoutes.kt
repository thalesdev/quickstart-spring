package io.quickstart.http.routes

import io.quickstart.domain.todos.ports.driver.*
import io.quickstart.http.middlewares.ErrorResponse
import io.quickstart.http.routes.request.CompleteTodoRequest
import io.quickstart.http.routes.request.CreateTodoRequest
import io.quickstart.http.routes.request.UpdateTodoRequest
import io.quickstart.http.routes.request.toCommand
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*


@RequestMapping("/todos")
@RestController
@Tag(name = "Todos")
class TodosRoutes(
    private val gettingTodo: GettingTodo,
    private val listingTodo: ListingTodo,
    private val creatingTodo: CreatingTodo,
    private val updatingTodo: UpdatingTodo,
    private val completingTodo: CompletingTodo
) {

    @GetMapping("/")
    suspend fun getAll() = listingTodo.getAll()

    @GetMapping("/{id}")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Todo found"),
            ApiResponse(
                responseCode = "404",
                description =
                "Todo not found",
                content = [Content(
                    schema = Schema(
                        implementation = ErrorResponse::class
                    )
                )]
            )
        ]
    )
    suspend fun getById(@PathVariable id: Int) =
        gettingTodo.getById(id)

    @PostMapping("/")
    suspend fun create(@RequestBody body: CreateTodoRequest) =
        creatingTodo.create(body.toCommand())

    @PutMapping("/{id}")
    suspend fun update(@PathVariable id: Int, @RequestBody body: UpdateTodoRequest) =
        updatingTodo.update(body.toCommand(id))

    @PatchMapping("/{id}/complete")
    suspend fun complete(@PathVariable id: Int, @RequestBody body: CompleteTodoRequest) =
        completingTodo.complete(body.toCommand(id))
}