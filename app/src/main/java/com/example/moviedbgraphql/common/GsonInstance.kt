package com.example.moviedbgraphql.common

import com.google.gson.Gson

object GsonInstance {
    private var gson: Gson? = null

    fun get(): Gson {
        val gson = gson ?: Gson()
        this.gson = gson
        return gson
    }
}