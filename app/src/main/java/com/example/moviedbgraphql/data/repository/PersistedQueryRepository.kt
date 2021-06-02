package com.example.moviedbgraphql.data.repository

import com.example.moviedbgraphql.data.dao.PersistedQueryDao
import com.example.moviedbgraphql.data.model.PersistedQuery
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class PersistedQueryRepository @Inject constructor(
    private val persistedQueryDao: PersistedQueryDao
) {

    private fun String.sha256(): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    fun getQueryHash(query: String): PersistedQuery? {
        return persistedQueryDao.find(query)
    }

    fun persistQuery(query: String): PersistedQuery {
        val sha256 = query.sha256()
        val queryToPersist = PersistedQuery(query, sha256)
        persistedQueryDao.insert(queryToPersist)
        return queryToPersist
    }
}