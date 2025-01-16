package com.reyhanpa.androidtest.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reyhanpa.androidtest.databinding.ActivityMainBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}