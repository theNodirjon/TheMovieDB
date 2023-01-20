package com.exemple.TheMovieDB.adapter.detail.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exemple.TheMovieDB.databinding.DetailMainListBinding
import com.exemple.TheMovieDB.model.DetaiListData.DetailListData
import com.exemple.TheMovieDB.model.response.base.BaseType

class DetailAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    var onDetailListClick: OnDetailListClick? = null


    var data = ArrayList<DetailListData>()
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(DetailMainListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindData(data[position], onDetailListClick)
    }

    override fun getItemCount(): Int = data.size

    interface OnDetailListClick {
        fun onDetailClick(subItem: BaseType)
    }

}