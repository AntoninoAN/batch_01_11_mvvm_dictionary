package com.example.urbandictionarynike.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.urbandictionarynike.model.DefinitionItem

@Dao
interface DictionaryDao {
    @Insert(onConflict = REPLACE)
    suspend fun refreshCache(newTerm: DefinitionItem)

    @Query("SELECT * FROM DefinitionItem")
    suspend fun getTerms(): List<DefinitionItem>

    @Query("SELECT * FROM DefinitionItem WHERE word LIKE :term")
    suspend fun containTerm(term: String): List<DefinitionItem>
}