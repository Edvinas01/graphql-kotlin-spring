package com.edd.graphql.app.role

import com.edd.graphql.app.user.User
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Service

@Service
class RoleService {

    private val roles = mutableListOf<Role>()
    private var rollingId = 1L

    /**
     * Add a new role with the given role name.
     *
     * @return added role.
     */
    fun add(name: String) = Role(rollingId++, name).apply {
        roles.add(this)
    }

    /**
     * @return list of roles filtered by data fetching environment.
     */
    fun getRoles(env: DataFetchingEnvironment): List<Role> {
        val src = env.getSource<Any>()
        if (src is User) {
            return roles.filter {
                src.roles.contains(it.id)
            }
        }
        return roles
    }
}