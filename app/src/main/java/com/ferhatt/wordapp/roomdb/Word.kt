package com.ferhatt.wordapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(var wordName : String,
                var wordDescription : String,
                var imageUrl : String,
                @PrimaryKey(autoGenerate = true)
                var id : Int? = null)
