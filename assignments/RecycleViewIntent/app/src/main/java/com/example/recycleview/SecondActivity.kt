package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.example.recycleview.databinding.ActivitySecondBinding
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras ?: return
        val titleString = extras.getString("tString") // retrieve tString from intent.extras
        val detailsString = extras.getString("dString")
        val imageString = extras.getString("iString")

        binding.titleText.text = titleString  // set tvTitle to titleString
        binding.itemDetails.text = detailsString
        binding.img.setImageResource(imageString!!.toInt())
    }
}