package com.deloitte.flickerimageviewer.ui.models

data class Photo(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int
)