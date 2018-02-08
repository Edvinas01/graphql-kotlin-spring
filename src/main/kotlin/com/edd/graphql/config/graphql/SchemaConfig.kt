package com.edd.graphql.config.graphql

import com.edd.graphql.app.role.RoleService
import com.edd.graphql.app.user.UserService
import org.springframework.context.annotation.Configuration
import graphql.Scalars
import graphql.schema.*
import org.springframework.context.annotation.Bean

@Configuration
class SchemaConfig(
        private val roleService: RoleService,
        private val userService: UserService
) {

    @Bean
    fun schema(): GraphQLSchema {
        val rolesFetcher = DataFetcher { env -> roleService.getRoles(env) }
        val usersFetcher = DataFetcher { env -> userService.getUsers(env) }

        val roleType = objectType("role") {
            description = "Access roles"

            field("id") {
                description = "Unique id"
                type = Scalars.GraphQLLong
            }
            field("name") {
                description = "Unique name"
                type = Scalars.GraphQLString
            }
        }

        val userType = objectType("user") {
            description = "Registered user"

            field("id") {
                description = "Unique id"
                type = Scalars.GraphQLLong
            }
            field("email") {
                description = "Unique email address"
                type = Scalars.GraphQLString
            }
            field("roles") {
                description = "Granted access roles"
                type = GraphQLList(roleType)
                data = rolesFetcher
            }
        }

        val rootQueryType = objectType("query") {
            description = "Root query type"

            field("roles") {
                description = "Overview of all roles"
                type = GraphQLList(roleType)
                data = rolesFetcher
            }
            field("users") {
                description = "Overview of all users"
                type = GraphQLList(userType)
                data = usersFetcher

                arg("email") {
                    type = Scalars.GraphQLString
                }
            }
        }
        return GraphQLSchema
                .newSchema()
                .query(rootQueryType)
                .build()
    }
}