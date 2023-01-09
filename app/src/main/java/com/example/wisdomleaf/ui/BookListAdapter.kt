package com.example.wisdomleaf.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wisdomleaf.R
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.google.android.material.textview.MaterialTextView

class BookListAdapter(
    val list: List<BookListResponseItem>,
    val requireContext: Context
) :
    RecyclerView.Adapter<CoinsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_layout, parent, false)
        return CoinsViewHolder(view)

    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val data = list[position]
        val scale = Math.pow(10.0, 2.0)

        holder.name.text = data.author
        holder.id.text = data.id
        Glide.with(requireContext)
            .load(data.download_url)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CoinsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: MaterialTextView = view.findViewById(R.id.author_name_tv)
    val id: MaterialTextView = view.findViewById(R.id.id_tv)
    val image: ImageView = view.findViewById(R.id.author_img)
}