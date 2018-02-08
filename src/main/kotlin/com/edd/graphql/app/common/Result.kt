package com.edd.graphql.app.common

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Common API response result.
 */
data class Result<out T>(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        val data: T? = null,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val errors: List<String> = emptyList()
)