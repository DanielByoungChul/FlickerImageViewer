package com.deloitte.flickerimageviewer.api.response

import com.deloitte.flickerimageviewer.models.Photos
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponsePhotos(
    @Expose
    @SerializedName("photos")
    val photos: Photos? = null
)