package com.edd.graphql.app.graphql

import com.edd.graphql.app.common.Result
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/graphql")
class GraphQLController(private val executor: GraphQLExecutor) {

    @PostMapping
    fun execute(@RequestBody request: GraphQLRequest) =
            executor.execute(request).let {
                Result<Any>(it.getData(), it.errors.map { it.message })
            }
}