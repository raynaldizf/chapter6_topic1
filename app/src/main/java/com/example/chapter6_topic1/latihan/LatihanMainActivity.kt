package com.example.chapter6_topic1.latihan

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter6_topic1.R
import com.example.chapter6_topic1.databinding.ActivityLatihanMainBinding

class LatihanMainActivity : AppCompatActivity() {
    lateinit var binding : ActivityLatihanMainBinding
    private val viewModel : BlurViewModel by viewModels { BlurViewModel.BlurViewModelFactory(application) }

    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImg.setOnClickListener{
            pickImage()
        }
        binding.btnUpdate.setOnClickListener{
            viewModel.applyBlur(1)
        }
    }

    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.btnImg.setImageURI(data?.data)
        }
    }
}