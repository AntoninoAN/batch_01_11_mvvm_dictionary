package com.example.urbandictionarynike.model.local

import android.content.Context
import androidx.core.content.edit
import com.example.urbandictionarynike.model.DefinitionItem
import com.example.urbandictionarynike.model.DefinitionResponse
import java.util.*

class CacheManager(
    private val dictionaryDao: DictionaryDao,
    private val context: Context
) {

    companion object {
        private const val CONNECTIVITY_SP = "DictionaryNetworkRequest"
        private const val DATE_REQUEST = "LastRequest"
    }

    fun isStale(): Boolean {
        // In Java
        // getSharedPreferences("name", Context.MODE_PRIVATE)
        // .getString | getBoolean | getChar
        // to Update
        // getSharedPreferences("name")
        // .edit("key", "defaultValue")

        val oldDateString = context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .getString(DATE_REQUEST, null) ?: return false

        val currentDate = Calendar.getInstance().timeInMillis - (5 * 60 * 1000)

        return oldDateString.toLong() > currentDate
    }

    fun updateNewNetworkRequest() {
        context.getSharedPreferences(CONNECTIVITY_SP, Context.MODE_PRIVATE)
            .edit {
                putString(DATE_REQUEST, Calendar.getInstance().timeInMillis.toString())
            }
    }

    suspend fun contains(term: String): Boolean {
        return dictionaryDao.containTerm(term).isNotEmpty()
    }

    suspend fun getDefinitions(term: String): List<DefinitionItem> {
        return dictionaryDao.containTerm(term)
    }

    suspend fun updateCache(response: DefinitionResponse) {
        response.list.forEach {
            dictionaryDao.refreshCache(it)
        }
    }
}