package com.reyhanpa.androidtest.ui.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reyhanpa.androidtest.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}