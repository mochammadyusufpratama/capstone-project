package com.dicoding.capstoneproject.homepage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstoneproject.databinding.ActivityNewsDetailBinding
import com.google.gson.Gson

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_NEWS_JSON = "extra_news_json"

        fun start(context: Context, newsJson: String) {
            val intent = Intent(context, NewsDetailActivity::class.java).apply {
                putExtra(EXTRA_NEWS_JSON, newsJson)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsJson = intent.getStringExtra(EXTRA_NEWS_JSON)
        val newsItem = Gson().fromJson(newsJson, ItemsItem::class.java)
        newsItem?.let { showNewsDetail(it) }
    }

    private fun showNewsDetail(newsItem: ItemsItem) {
        binding.titleTextView.text = newsItem.title
        binding.descriptionTextView.text = newsItem.snippet
        // Tampilkan data lain yang diperlukan
    }
}


