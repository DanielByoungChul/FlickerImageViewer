package com.deloitte.flickerimageviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.databinding.MainActivityBinding
import com.deloitte.flickerimageviewer.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment.newInstance())
                .commitNow()
        }
    }
}
