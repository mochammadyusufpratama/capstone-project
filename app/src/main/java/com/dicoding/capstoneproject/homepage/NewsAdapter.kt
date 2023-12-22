package com.dicoding.capstoneproject.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.databinding.ItemNewsBinding
import com.google.gson.Gson
import android.content.Context


class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    private var items: List<ItemsItem?>? = null

    fun submitList(newItems: List<ItemsItem?>?) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = items?.get(position)
        holder.bind(newsItem)
        holder.itemView.setOnClickListener {
            val gson = Gson()
            val newsJson = gson.toJson(newsItem)
            NewsDetailActivity.start(context, newsJson)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: ItemsItem?) {
            // Bind data ke tampilan ViewHolder sesuai dengan kebutuhan Anda
            binding.titleTextView.text = newsItem?.title
            binding.snippetTextView.text = newsItem?.snippet
            // Tambahan lainnya sesuai dengan kebutuhan
        }
    }
}
