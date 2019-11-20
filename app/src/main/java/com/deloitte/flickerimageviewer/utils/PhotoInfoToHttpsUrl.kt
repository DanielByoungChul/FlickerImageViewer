package com.deloitte.flickerimageviewer.utils

import com.deloitte.flickerimageviewer.models.Photo
import java.lang.StringBuilder

/**
 * PhotoInfoToHttpsUrl
 * - convert PhotoInfo to HttpsUrl String
 *   http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
 *   http://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg
 * */

object PhotoInfoToHttpsUrl {
    fun convert(photoInfo: Photo): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("https://farm")
            .append(photoInfo.farm)
            .append(".static.flickr.com/")
            .append(photoInfo.server)
            .append("/")
            .append(photoInfo.id)
            .append("_")
            .append(photoInfo.secret)
            .append(".jpg")

        return stringBuilder.toString()
    }
}