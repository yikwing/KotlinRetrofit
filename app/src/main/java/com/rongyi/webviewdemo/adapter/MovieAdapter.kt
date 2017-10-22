package com.rongyi.webviewdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rongyi.webviewdemo.bean.MovieEntity
import com.rongyi.webviewdemo.R
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/10/22
 */
class MovieAdapter(var items: List<MovieEntity.SubjectsBean>, val itemClickListener: (MovieEntity.SubjectsBean) -> Unit, val mComtext: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(items[position])
        Glide.with(mComtext).load(items.get(position).images.small).into(holder.view.mImage)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflate = View.inflate(mComtext, R.layout.item_movie, null)
        return ViewHolder(inflate, itemClickListener)
    }


    class ViewHolder(val view: View, val itemClickListener: (MovieEntity.SubjectsBean) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(bean: MovieEntity.SubjectsBean) {
            view.mTitle.text = bean.title
            view.setOnClickListener {
                itemClickListener(bean)
            }
        }
    }
}