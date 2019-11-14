package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.deloitte.flickerimageviewer.ui.models.Photo

class MainViewModel : ViewModel() {

    private val _index: MutableLiveData<String> = MutableLiveData()
    val photos: LiveData<List<Photo>> = Transformations.switchMap(_index) {
        MainRespository.getPhotos()
    }

    fun setIndex(index:String) {
        _index.value = index
    }
}
