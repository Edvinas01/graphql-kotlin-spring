package com.edd.graphql.app.user

import com.edd.graphql.app.role.Role
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service

@Service
class UserService {

    private val users = mutableListOf<User>()
    private var rollingId = 1L

    /**
     * Add a new user with the given email address and roles.
     *
     * @return added user.
     */
    fun add(email: String, vararg roles: Role) = User(
            rollingId++, email, roles.map { it.id }.toSet()
    ).let {
        users.add(it)
    }

    /**
     * @return list of users filtered by data fetching environment.
     */
    fun getUsers(env: DataFetchingEnvironment): List<User> {
        val email = env.arguments["email"]
        if (email != null) {
            return users.filter { it.email == email }
        }
        return users
    }
}