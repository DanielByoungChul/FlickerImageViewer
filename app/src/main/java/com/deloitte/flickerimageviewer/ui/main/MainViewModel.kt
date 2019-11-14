package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.deloitte.flickerimageviewer.ui.models.Photo
import com.deloitte.flickerimageviewer.ui.util.PreferenceUtil

class MainViewModel : ViewModel() {

    private val _message: MutableLiveData<String> = MutableLiveData()
    val photos: LiveData<List<Photo>> = Transformations.switchMap(_message) {
        MainRespository.getPhotos()
    }

    fun setMessage(message:String) {

        _message.value = message

    }
}
