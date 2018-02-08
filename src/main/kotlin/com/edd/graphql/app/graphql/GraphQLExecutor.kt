package com.edd.graphql.app.graphql

import graphql.ExecutionInput
import graphql.ExecutionResult
import graphql.GraphQL
import org.springframework.stereotype.Component

@Component
class GraphQLExecutor(private val graphQL: GraphQL) {

    /**
     * Execute GraphQL request and form a result.
     *
     * @return GraphQL execution result.
     */
    fun execute(request: GraphQLRequest): ExecutionResult = graphQL
            .execute(ExecutionInput
                    .newExecutionInput()
                    .operationName(request.operationName)
                    .query(request.query)
                    .variables(request.variables ?: emptyMap())
            )
}