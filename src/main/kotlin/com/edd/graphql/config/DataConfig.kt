package com.edd.graphql.config

import com.edd.graphql.app.role.RoleService
import com.edd.graphql.app.user.UserService
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class DataConfig(
        private val roleService: RoleService,
        private val userService: UserService
) {

    @PostConstruct
    fun init() {
        val admin = roleService.add("Admin")
        val user = roleService.add("User")

        userService.add("pete@gmail.com", admin, user)
        userService.add("carl@gmail.com", user)
        userService.add("steve@gmail.com", admin)
    }
}