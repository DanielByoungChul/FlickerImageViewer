package com.deloitte.flickerimageviewer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.ui.interfaces.ILoadMore
import com.deloitte.flickerimageviewer.ui.models.Photo
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.lang.StringBuilder

class PhotoListRecyclerViewAdapter(val context: Context, recyclerView: RecyclerView, val photos: List<Photo>):
    RecyclerView.Adapter<PhotoListRecyclerViewAdapter.PhotoItemViewHolder>() {

    internal var loadMore: ILoadMore? = null
    internal var isLoading: Boolean = false
    internal var visibleThreshold = 20
    internal var lastVisibleItem: Int = 0
    internal var totalItemCount: Int = 0


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
            .load(getHttpsUrl(photoInfo))
            .placeholder(R.drawable.ic_broken_image)
            .centerCrop()
            .into(holder.photoImageView)
    }

    /**
     * http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
     * http://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg
     * */
    private fun getHttpsUrl(photoInfo: Photo): String {
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

        val urlOutput = stringBuilder.toString()

        println("urlOutput = $urlOutput")
        return urlOutput
    }

    fun setLoad(iLoadMore: ILoadMore) {
        this.loadMore = iLoadMore
    }

    inner class PhotoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView =  itemView.ivPhoto
    }

}