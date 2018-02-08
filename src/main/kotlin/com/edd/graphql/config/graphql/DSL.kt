package com.edd.graphql.config.graphql

import graphql.schema.*

@DslMarker
annotation class GraphQLMarker

/**
 * Definition for GraphQL object type.
 */
@GraphQLMarker
class ObjectTypeDef(private val fields: MutableList<GraphQLFieldDefinition>) {

    var description: String? = null

    /**
     * Add a new field under this object with the given name.
     */
    fun field(name: String, decorator: FieldDef.() -> Unit) {
        val args = mutableListOf<GraphQLArgument>()
        val field = FieldDef(args = args)

        decorator(field)

        val graphQLField = GraphQLFieldDefinition
                .newFieldDefinition()
                .name(name)
                .description(field.description)
                .type(field.type)

        // dataFetcher will throw an exception if null is provided.
        field.data?.let {
            graphQLField.dataFetcher(it)
        }

        args.forEach {
            graphQLField.argument(it)
        }

        fields += graphQLField.build()
    }
}

/**
 * Definition for GraphQL field.
 */
@GraphQLMarker
class FieldDef(
        var description: String? = null,
        var type: GraphQLOutputType? = null,
        var data: DataFetcher<*>? = null,
        private val args: MutableList<GraphQLArgument>
) {

    /**
     * Add a new argument under this object with the given name.
     */
    fun arg(name: String, decorator: ArgDef.() -> Unit) {
        val arg = ArgDef()

        decorator(arg)

        args += GraphQLArgument
                .newArgument()
                .name(name)
                .description(arg.description)
                .type(arg.type)
                .build()
    }
}

/**
 * Definition for GraphQL argument.
 */
@GraphQLMarker
class ArgDef(
        var description: String? = null,
        var type: GraphQLInputType? = null
)

/**
 * Create a new object type with the given name.
 */
fun objectType(name: String, decorator: ObjectTypeDef.() -> Unit): GraphQLObjectType {
    val fields = mutableListOf<GraphQLFieldDefinition>()
    val query = ObjectTypeDef(fields)

    decorator(query)

    val graphQLObjectType = GraphQLObjectType
            .newObject()
            .description(query.description)
            .name(name)

    fields.forEach {
        graphQLObjectType.field(it)
    }
    return graphQLObjectType.build()
}