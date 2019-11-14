package com.deloitte.flickerimageviewer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deloitte.flickerimageviewer.R
import com.deloitte.flickerimageviewer.ui.models.Photo
import java.lang.StringBuilder

class PhotoListRecyclerViewAdapter(val context: Context, val photos: List<Photo>):
    RecyclerView.Adapter<PhotoListRecyclerViewAdapter.PhotoListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return PhotoListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        showImageView1(holder,photos.get(3*position))
        showImageView2(holder,photos.get(3*position+1))
        showImageView3(holder,photos.get(3*position+2))
    }

    fun showImageView1(holder: PhotoListViewHolder, photoInfo: Photo) {
        Glide
            .with(context)
            .load(getHttpsUrl(photoInfo))
            .placeholder(R.drawable.ic_broken_image)
            .centerCrop()
            .into(holder.photoImageView1)
    }

    fun showImageView2(holder: PhotoListViewHolder, photoInfo: Photo) {
        Glide
            .with(context)
            .load(getHttpsUrl(photoInfo))
            .placeholder(R.drawable.ic_broken_image)
            .centerCrop()
            .into(holder.photoImageView2)
    }

    fun showImageView3(holder: PhotoListViewHolder, photoInfo: Photo) {
        Glide
            .with(context)
            .load(getHttpsUrl(photoInfo))
            .placeholder(R.drawable.ic_broken_image)
            .centerCrop()
            .into(holder.photoImageView3)
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


    inner class PhotoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView1 =  itemView.findViewById<ImageView>(R.id.photo_image1)
        val photoImageView2 =  itemView.findViewById<ImageView>(R.id.photo_image2)
        val photoImageView3 =  itemView.findViewById<ImageView>(R.id.photo_image3)
    }
}