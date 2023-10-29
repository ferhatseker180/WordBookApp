package com.ferhatt.wordapp.repo

import androidx.lifecycle.LiveData
import com.ferhatt.wordapp.model.ImageResponse
import com.ferhatt.wordapp.roomdb.Word
import com.ferhatt.wordapp.util.Resource

interface WordRepoInterface {

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

    fun getWord() : LiveData<List<Word>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>

}