package com.example.urbandictionarynike.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DefinitionResponse(
    val list: List<DefinitionItem>
)

@Entity
data class DefinitionItem(
    val definition: String,
    val thumbs_up: Int,
    val thumbs_down: Int,
    @PrimaryKey
    val word: String
)
