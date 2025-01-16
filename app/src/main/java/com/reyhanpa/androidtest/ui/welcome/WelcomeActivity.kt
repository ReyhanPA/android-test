package com.reyhanpa.androidtest.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reyhanpa.androidtest.R
import com.reyhanpa.androidtest.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_second_screen)
    }
}