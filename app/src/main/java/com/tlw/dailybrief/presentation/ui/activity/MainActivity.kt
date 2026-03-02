package com.tlw.dailybrief.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.tlw.dailybrief.R
import com.tlw.dailybrief.databinding.ActivityMainBinding
import com.tlw.dailybrief.presentation.ui.adapter.NewsAdapter
import com.tlw.dailybrief.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<NewsViewModel>()
    private val newsAdapter by lazy {
        NewsAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerview()
        observeNewsData()

        binding.apply {
            btnRefresh.setOnClickListener {
                viewModel.getNews()
            }
        }
    }

    private fun setupRecyclerview() {
        binding.rvNews.apply {
            adapter = NewsAdapter()
            setHasFixedSize(true)
        }
    }

    private fun observeNewsData() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.uiState.collect { state ->
                state.data.let {
                    newsAdapter.updateData(it)
                }
            }
        }
    }
}