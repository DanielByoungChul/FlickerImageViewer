package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.deloitte.flickerimageviewer.models.Photo

class MainViewModel : ViewModel() {

    private val _message: MutableLiveData<String> = MutableLiveData()
    val photos: LiveData<List<Photo>> = Transformations.switchMap(_message) {
        MainRespository.getPhotos()
    }

    init {
        initViewModel("Init")
    }

    fun initViewModel(message:String) {
        _message.value = message
    }

    fun reloadViewModel(message: String) {
        _message.value = message
    }
}
