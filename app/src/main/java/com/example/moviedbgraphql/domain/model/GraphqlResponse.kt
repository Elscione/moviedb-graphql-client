package com.example.moviedbgraphql.domain.model

import com.example.moviedbgraphql.common.GsonInstance
import com.google.gson.annotations.SerializedName

data class GraphqlResponse(
    @SerializedName("data")
    private val data: Any,
    @SerializedName("errors")
    val errors: List<Error?>? = listOf()
) {
    data class Error(
        @SerializedName("extensions")
        val extensions: Extensions? = Extensions(),
        @SerializedName("message")
        val message: String? = ""
    ) {
        data class Extensions(
            @SerializedName("code")
            val code: String? = "",
            @SerializedName("exception")
            val exception: Exception? = Exception()
        ) {
            data class Exception(
                @SerializedName("stacktrace")
                val stacktrace: List<String?>? = listOf()
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getData(type: Class<*>): T {
        val dataString = GsonInstance.get().toJson(data)
        return GsonInstance.get().fromJson(dataString, type) as T
    }
}