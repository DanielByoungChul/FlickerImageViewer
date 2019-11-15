package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.ui.adapters.PhotoListRecyclerViewAdapter
import com.deloitte.flickerimageviewer.ui.interfaces.ILoadMore

class MainFragment : Fragment(), ILoadMore {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        progressBar = view.findViewById(R.id.progressbar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_photo_list)
        recyclerView.layoutManager = GridLayoutManager(context,3)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.photos.observe(this, Observer {

            showProgress(false)
            val adapter = context?.let { it1 -> PhotoListRecyclerViewAdapter(it1,recyclerView,it) }

            recyclerView.adapter = adapter

            adapter?.setLoad(this)

        })

        return view
    }

    private fun showProgress(status: Boolean) {
        when(status) {
            true -> {
                progressBar.visibility = View.VISIBLE
                progressBar.isIndeterminate = true
            }

            false -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onLoadMore() {
        showProgress(true)
        println("onLoadMore --->>> onLoadMore")
        viewModel.reloadViewModel("load more photos")
    }
}
