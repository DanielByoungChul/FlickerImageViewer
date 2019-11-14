package com.deloitte.flickerimageviewer.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.ui.adapters.PhotoListRecyclerViewAdapter


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_photo_list)
        recyclerView.layoutManager = GridLayoutManager(context,3)
//        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        // GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.photos.observe(this, Observer {

            println("list of photo : $it")
            println("size of list of photo : ${it.size}")
            recyclerView.adapter = context?.let { context -> PhotoListRecyclerViewAdapter(context,it) }

        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        println("onActivityCreated")

    }

}
