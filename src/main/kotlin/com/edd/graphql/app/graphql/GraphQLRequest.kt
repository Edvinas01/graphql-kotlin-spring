package com.edd.graphql.app.graphql

data class GraphQLRequest(
        val operationName: String?,
        val query: String,
        val variables: Map<String, Any>?
)