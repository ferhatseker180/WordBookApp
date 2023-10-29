package com.ferhatt.wordapp.repo

import androidx.lifecycle.LiveData
import com.ferhatt.wordapp.api.RetrofitAPI
import com.ferhatt.wordapp.model.ImageResponse
import com.ferhatt.wordapp.roomdb.Word
import com.ferhatt.wordapp.roomdb.WordDao
import com.ferhatt.wordapp.util.Resource
import javax.inject.Inject

class WordRepository @Inject constructor (
    private val wordDao : WordDao,
    private val retrofitApi : RetrofitAPI) : WordRepoInterface  {

    override suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    override fun getWord(): LiveData<List<Word>> {
        return wordDao.observeWord()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }

}