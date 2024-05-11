package com.example.testing_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private var _binding: ActivityStartBinding? = null
    private val bind:ActivityStartBinding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(bind.root)
        Data.context = applicationContext
        Data.setData()
        bind.btnStart.setOnClickListener {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
        }
    }
}