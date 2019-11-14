package com.deloitte.flickerimageviewer.ui.api.response

import com.deloitte.flickerimageviewer.ui.models.Photos
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponsePhotos(
    @Expose
    @SerializedName("photos")
    val photos: Photos? = null
)