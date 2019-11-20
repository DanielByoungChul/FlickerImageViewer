package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.databinding.MainFragmentBinding
import com.deloitte.flickerimageviewer.ui.adapters.PhotoListRecyclerViewAdapter
import com.deloitte.flickerimageviewer.interfaces.ILoadMore
import com.deloitte.flickerimageviewer.models.Photo

class MainFragment : Fragment(), ILoadMore {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var binding: MainFragmentBinding
    var photoListRecyclerViewAdapter: PhotoListRecyclerViewAdapter? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var progressBar: ProgressBar
    var photoList = mutableListOf<Photo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        progressBar = binding.pagingProgressbar
        val recyclerView = binding.rvPhotoList
        recyclerView.layoutManager = GridLayoutManager(context,3)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.photos.observe(this, Observer {photoList ->
            showProgress(false)

            context?.let {
                if(photoListRecyclerViewAdapter == null) {
                    photoListRecyclerViewAdapter = PhotoListRecyclerViewAdapter(it, recyclerView)
                    photoListRecyclerViewAdapter?.setLoad(this)
                }
            }

            photoListRecyclerViewAdapter?.let {
                if(it.getPopulatedListSize()== 0) {
                    this.photoList = photoList.toList() as MutableList<Photo>
                    it.setData(this.photoList)
                    recyclerView.adapter = photoListRecyclerViewAdapter
                }else {
                    for(photo in photoList) {
                        this.photoList.add(photo)
                    }
                    it.notifyDataSetChanged()
                }
                it.setLoaded()
            }
        })

        return binding.root
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
