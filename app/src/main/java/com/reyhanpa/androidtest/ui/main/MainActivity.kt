package com.reyhanpa.androidtest.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.reyhanpa.androidtest.R
import com.reyhanpa.androidtest.databinding.ActivityMainBinding
import com.reyhanpa.androidtest.ui.welcome.WelcomeActivity
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            imgUser.setOnClickListener { startGallery() }
            btnCheck.setOnClickListener {
                val palindromeText = binding.etPalindrome.text.toString()
                if (palindromeText.isNotEmpty()) {
                    check(palindromeText)
                } else {
                    binding.etPalindromeLayout.error = getString(R.string.error_empty_text)
                }
            }
            btnNext.setOnClickListener {
                val nameText = binding.etName.text.toString()
                val palindromeText = binding.etPalindrome.text.toString()
                if (nameText.isNotEmpty() && palindromeText.isNotEmpty()) {
                    next()
                } else {
                    binding.etNameLayout.error = getString(R.string.error_empty_text)
                    binding.etPalindromeLayout.error = getString(R.string.error_empty_text)
                }
            }
        }
    }

    private val uCropResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val resultUri = UCrop.getOutput(result.data!!)
            if (resultUri != null) {
                currentImageUri = resultUri
                showImage()
            }
        } else if (result.resultCode == UCrop.RESULT_ERROR) {
            val error = UCrop.getError(result.data!!)
            showToast(error?.message ?: getString(R.string.error_cropping_failed))
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { imageUri: Uri? ->
        if (imageUri != null) {
            val timeStamp = SystemClock.uptimeMillis()
            val destinationUri = Uri.fromFile(File(cacheDir, "image_crop-$timeStamp"))

            val uCropIntent = UCrop.of(imageUri, destinationUri)
                .withAspectRatio(1f, 1f)
                .getIntent(this)

            uCropResultLauncher.launch(uCropIntent)
        }
    }

    private fun startGallery() {
        launcherGallery.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    private fun showImage() {
        currentImageUri?.let {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .into(binding.imgUser)
            binding.imgUserIcon.visibility = View.GONE
        }
    }

    private fun check(sentence: String) {
        val cleanedSentence = sentence.replace(" ", "").lowercase(Locale.ROOT)
        if (cleanedSentence == cleanedSentence.reversed()) {
            showToast(resources.getString(R.string.is_palindrome))
        } else {
            showToast(resources.getString(R.string.not_palindrome))
        }
    }

    private fun next() {
        val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(sentence: String) {
        Toast.makeText(this, sentence, Toast.LENGTH_SHORT).show()
    }
}