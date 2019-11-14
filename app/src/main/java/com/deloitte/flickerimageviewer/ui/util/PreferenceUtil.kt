package com.deloitte.flickerimageviewer.ui.util

import android.content.Context
import androidx.preference.PreferenceManager

class PreferenceUtil {
    companion object {

        const val LAST_KNOWN_MESSAGE = "com.deloitte.flickerimageviewer.last.known_message"

        fun setLastKnownMessage(context: Context, message: String) {
            val prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            prefEditor.putString(LAST_KNOWN_MESSAGE,message)
            prefEditor.apply()
        }

        fun getLastKnownMessage(context: Context): String? {
            val preference = PreferenceManager.getDefaultSharedPreferences(context)
            return preference.getString(LAST_KNOWN_MESSAGE,null)
        }

    }

}