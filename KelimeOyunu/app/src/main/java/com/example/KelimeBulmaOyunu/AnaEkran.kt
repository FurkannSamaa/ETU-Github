package com.example.KelimeBulmaOyunu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.KelimeBulmaOyunu.databinding.ActivityAnaEkranBinding


class AnaEkran : AppCompatActivity() {
    private lateinit var binding: ActivityAnaEkranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnaEkranBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.oynaButton.setOnClickListener{
            var oyunEkrani = Intent(applicationContext,OyunEkrani::class.java)
            startActivity(oyunEkrani)
        }
        binding.cikisButon.setOnClickListener{
            System.exit(0)

        }
    }
}