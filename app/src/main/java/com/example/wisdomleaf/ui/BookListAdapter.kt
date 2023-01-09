package com.example.wisdomleaf.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wisdomleaf.R
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.google.android.material.textview.MaterialTextView

class BookListAdapter(
    val list: List<BookListResponseItem>,
    private val requireContext: Context
) :
    RecyclerView.Adapter<BooksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list_layout, parent, false)
        return BooksViewHolder(view)

    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val data = list[position]

        holder.name.text = data.author
        holder.id.text = data.id
        Glide.with(requireContext)
            .load(data.download_url)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_placeholder)
            .into(holder.image)

        holder.layout.setOnClickListener {
            showDescription(list[position].author)
        }
    }

    private fun showDescription(text: String) {
        val builder = AlertDialog.Builder(requireContext)
        builder.setTitle("Author Name")

        builder.setMessage(text)
            .setIcon(R.drawable.ic_logo)
            .setCancelable(false)
        builder.setIcon(android.R.drawable.ic_dialog_alert)


        builder.setPositiveButton("Okay") { dialog, _ ->
            dialog.dismiss()
        }

        /*builder.setNegativeButton("No") { dialog, _ ->

        }*/

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setIcon(R.drawable.ic_logo)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: MaterialTextView = view.findViewById(R.id.author_name_tv)
    val id: MaterialTextView = view.findViewById(R.id.id_tv)
    val image: ImageView = view.findViewById(R.id.author_img)
    val layout: ConstraintLayout = view.findViewById(R.id.item_layout)
}