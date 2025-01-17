package com.reyhanpa.androidtest.ui.users

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reyhanpa.androidtest.databinding.ActivityUsersBinding
import com.reyhanpa.androidtest.utils.ViewModelFactory

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var adapter: UsersAdapter
    private val viewModel by viewModels<UsersViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()
        getData()
        setupRecyclerView()
        setupObservers()
        setupActions()
    }

    private fun getData() {
        viewModel.usernames.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvUsername.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        binding.rvUsername.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { event ->
            event.getContentIfNotHandled()?.let { message ->
                showError(message)
            }
        }
    }

    private fun setupActions() {
        binding.toolbarBack.setOnClickListener { finish() }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}