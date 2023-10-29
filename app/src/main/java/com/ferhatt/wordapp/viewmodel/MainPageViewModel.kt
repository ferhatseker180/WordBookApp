package com.ferhatt.wordapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatt.wordapp.model.ImageResponse
import com.ferhatt.wordapp.repo.WordRepoInterface
import com.ferhatt.wordapp.repo.WordRepository
import com.ferhatt.wordapp.roomdb.Word
import com.ferhatt.wordapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val repository: WordRepoInterface) : ViewModel() {

        val wordList = repository.getWord()

    // Image Api Fragment İçin Kullanılacak Model
    private val images = MutableLiveData<Resource<ImageResponse>>()
    // images'a dışarıdan set yapılamaz ama aşağıdaki değişkenle get yapılabilir.
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedUrl : LiveData<String>
        get() = selectedImage

    private var insertWordMsg = MutableLiveData<Resource<Word>>()
    val insertArtMessage : LiveData<Resource<Word>>
        get() = insertWordMsg

    fun resetInsertMsg(){
        insertWordMsg = MutableLiveData<Resource<Word>>()
    }

    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }

    fun insertWord(word: Word) = viewModelScope.launch {
        repository.insertWord(word)
    }

    fun makeWord(wordName : String,wordDescription : String){
        if (wordName.isEmpty() || wordDescription.isEmpty()){
            insertWordMsg.postValue(Resource.error("Enter word and description",null))
            return
        }

        val word = Word(wordName,wordDescription,selectedImage.value ?: "")
        insertWord(word)
        setSelectedImage("")
        insertWordMsg.postValue(Resource.success(word))
    }

    fun searchForImage(searchString : String){
        if (searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }


    }


}