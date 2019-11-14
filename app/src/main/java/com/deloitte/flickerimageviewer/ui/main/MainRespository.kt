package com.deloitte.flickerimageviewer.ui.main

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.deloitte.flickerimageviewer.ui.api.RetrofitBuilder
import com.deloitte.flickerimageviewer.ui.models.Photo
import kotlinx.coroutines.*

class MainRespository {

    companion object {

        const val METHOD = "flickr.photos.search"
        const val API_KEY = "eea3a1e8ea9911af3a4e1d3dfb66f3a3"
        const val TEXT = "kittens"
        const val FORMAT = "json"
        const val NO_JSON_CALLBACK = "1"

        lateinit var job: Job


        fun getPhotos(): LiveData<List<Photo>> {

            job = Job()

            return object: LiveData<List<Photo>>() {
                override fun onActive() {
                    super.onActive()

                    CoroutineScope(Dispatchers.IO + job).launch {
                        val responsePhoto = RetrofitBuilder.apiService.getPhotos(METHOD, API_KEY,
                            TEXT, FORMAT, NO_JSON_CALLBACK)

                        withContext(Dispatchers.Main) {

                            responsePhoto.photos?.let {photos ->
                                value = photos.photo
                            }

                            cancelJob()
                        }
                    }

                }
            }
        }

        fun cancelJob() {
            job.cancel()
        }


    }

}