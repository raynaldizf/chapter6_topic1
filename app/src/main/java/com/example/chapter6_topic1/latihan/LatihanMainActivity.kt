package com.example.chapter6_topic1.latihan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter6_topic1.databinding.ActivityLatihanMainBinding

class LatihanMainActivity : AppCompatActivity() {
    lateinit var binding : ActivityLatihanMainBinding
    private val viewModel : BlurViewModel by viewModels { BlurViewModel.BlurViewModelFactory(application) }
    private var imageUri: Uri? = null

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
        var inten = Intent(Intent.ACTION_PICK)
        inten.type= "image/*"
        startActivityForResult(inten, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.btnImg.setImageResource(pickImage())
        }
    }
}