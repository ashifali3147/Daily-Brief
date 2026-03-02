package com.tlw.dailybrief.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tlw.dailybrief.databinding.NewsTileBinding
import com.tlw.dailybrief.domain.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsList = mutableListOf<News>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val binding = NewsTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            tvTitle.text = newsList[position].title
            tvDescription.text = newsList[position].description
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateData(data: List<News>) {
        newsList.clear()
        newsList.addAll(data)
        notifyDataSetChanged()
    }


    class NewsViewHolder(val binding: NewsTileBinding) : RecyclerView.ViewHolder(binding.root)
}