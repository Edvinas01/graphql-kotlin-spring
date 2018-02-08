package com.edd.graphql.config

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLConfig {

    @Bean
    fun graphQL(schema: GraphQLSchema): GraphQL = GraphQL
            .newGraphQL(schema)
            .build()
}