package com.reyhanpa.androidtest.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reyhanpa.androidtest.databinding.ActivityWelcomeBinding
import com.reyhanpa.androidtest.ui.users.UsersActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toolbarBack.setOnClickListener { finish() }
            name.text = intent.getStringExtra("EXTRA_NAME")
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@WelcomeActivity, UsersActivity::class.java)
                startActivity(intent)
            }
        }
    }
}