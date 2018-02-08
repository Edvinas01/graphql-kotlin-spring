package com.edd.graphql.app.user

data class User(
        val id: Long,
        val email: String,
        val roles: Set<Long>
)