package com.deloitte.flickerimageviewer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.interfaces.ILoadMore
import com.deloitte.flickerimageviewer.models.Photo
import com.deloitte.flickerimageviewer.utils.PhotoInfoToHttpsUrl
import kotlinx.android.synthetic.main.list_item.view.*

class PhotoListRecyclerViewAdapter(val context: Context, recyclerView: RecyclerView, val photos: List<Photo>):
    RecyclerView.Adapter<PhotoListRecyclerViewAdapter.PhotoItemViewHolder>() {

    internal var loadMore: ILoadMore? = null
    internal var isLoading: Boolean = false
    internal var visibleThreshold = 20
    internal var lastVisibleItem: Int = 0
    internal var totalItemCount: Int = 0

    lateinit var photoList: List<Photo>


    init {
        val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = gridLayoutManager.itemCount
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition()

                if(!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    if(loadMore != null)
                        loadMore!!.onLoadMore()
                    isLoading = true
                }
            }
        })

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return PhotoItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        showImageView(holder, photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }


    fun setLoaded() {
        isLoading = false
    }

    fun showImageView(holder: PhotoItemViewHolder, photoInfo: Photo) {
        Glide
            .with(context)
            .load(PhotoInfoToHttpsUrl.convert(photoInfo))
            .centerCrop()
            .into(holder.photoImageView)
    }

    fun setLoad(iLoadMore: ILoadMore) {
        this.loadMore = iLoadMore
    }

    inner class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView =  itemView.ivPhoto
    }

}