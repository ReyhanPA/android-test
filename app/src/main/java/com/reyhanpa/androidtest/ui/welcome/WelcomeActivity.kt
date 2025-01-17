package com.reyhanpa.androidtest.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.reyhanpa.androidtest.databinding.ActivityWelcomeBinding
import com.reyhanpa.androidtest.ui.users.UsersActivity
import com.reyhanpa.androidtest.utils.ViewModelFactory

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private val viewModel by viewModels<WelcomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.getSession().observe(this) { user ->
            binding.name.text = user.name
        }
    }

    private fun setupListeners() {
        binding.apply {
            toolbarBack.setOnClickListener { finish() }
            username.text = intent.getStringExtra("EXTRA_USERNAME")
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@WelcomeActivity, UsersActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
