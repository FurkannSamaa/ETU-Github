package com.example.KelimeBulmaOyunu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.KelimeBulmaOyunu.databinding.ActivityOyunEkraniBinding



class OyunEkrani : AppCompatActivity() {
    private lateinit var binding: ActivityOyunEkraniBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var builder : AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        sharedPreferences = getSharedPreferences("com.example.kelimetahmin", MODE_PRIVATE)
        highScore = sharedPreferences.getInt("HighScore",0)
        binding.highScore.text = "HighScore $highScore"
        start()
        sure()
        builder = AlertDialog.Builder(this)
    }




    var kategoriler = arrayOf("İsim","Şehir","Hayvan","Meslek")
    val isimler = arrayOf("Furkan","Hatice","Sude","Ebrar","Esra","Duygu","Umut","Kerem","Hazar","Hakan")
    val sehirler = arrayOf("Erzurum","Ankara","Kastamonu","Kocaeli","Giresun","Adana","Trabzon","Ordu","Antalya","Manisa")
    val hayvanlar = arrayOf("Aslan","Kaplan","Fil","Zebra","Fare","Kartal","Balina","Kedi","Timsah","Tilki")
    val meslekler = arrayOf("Doktor","Polis","Ressam","Astronot","Pilot","Avukat","Marangoz","Oyuncu","Asker","Futbolcu")

    var randomSayi=0
    var randomSayi2=0

    var kelime = ""
    var cizgi = Array(1){""}

    var puan =0
    var score=0
    var highScore: Int = 0
    var a=0
    var sayi=0

    fun sure(){
        var obje =  object : CountDownTimer(60000,1000){
            override fun onTick(count: Long) {
                binding.sure.text = "Sure : ${count/1000 + 1}"
            }
            override fun onFinish() {
                binding.sure.text = "Sure Bitti"
                if(score>highScore){
                    highScore=score
                    binding.highScore.text="HighScore: " + highScore
                    sharedPreferences.edit().putInt("HighScore",highScore).apply()
                }
                builder.setTitle("Game Over")
                    .setMessage("Süreniz bitti Skorunuz : ${score}")
                    .setCancelable(true)
                    .setPositiveButton("AnaMenü"){dialogInterface,it ->
                        var anaMenu = Intent(applicationContext,AnaEkran::class.java)

                        System.exit(0)
                    }
                    .setNeutralButton("Restart"){dialogInterface,it ->
                        var oyunEkrani = Intent(applicationContext,OyunEkrani::class.java)

                        startActivity(oyunEkrani)
                        System.exit(0)
                    }
                    builder.setCancelable(false)
                    .show()

            }
        }
        obje.start()
        }



    fun start(){


        sayi=0
        randomSayi = (0..3).random()
        randomSayi2 = (0..9).random()
        a =0
        binding.ipucu.text = kategoriler[randomSayi]
        binding.soru.text= ""

        if(randomSayi==0){

            kelime = isimler[randomSayi2]
            cizgi = Array(isimler[randomSayi2].length){"- "}
            puan = isimler[randomSayi2].length*100
            binding.puan.text = puan.toString()

            for(i in 0..isimler[randomSayi2].length-1){

                binding.soru.append(cizgi[a])
                a++
            }

        }
        else if (randomSayi==1){

            kelime = sehirler[randomSayi2]
            cizgi = Array(sehirler[randomSayi2].length){"- "}
            puan = sehirler[randomSayi2].length*100
            binding.puan.text = puan.toString()

            for(i in 0..sehirler[randomSayi2].length-1){
                binding.soru.append(cizgi[a])
                a++
            }

        }
        else if (randomSayi==2){

            kelime = hayvanlar[randomSayi2]
            cizgi = Array(hayvanlar[randomSayi2].length){"- "}
            puan = hayvanlar[randomSayi2].length*100
            binding.puan.text = puan.toString()

            for(i in 0..hayvanlar[randomSayi2].length-1){
                binding.soru.append(cizgi[a])
                a++
            }

        }
        else{
            kelime = meslekler[randomSayi2]
            cizgi = Array(meslekler[randomSayi2].length){"- "}
            puan = meslekler[randomSayi2].length*100
            binding.puan.text = puan.toString()

            for(i in 0..meslekler[randomSayi2].length-1){
                binding.soru.append(cizgi[a])
                a++
            }

        }






    }
    fun setupBinding(){
        binding = ActivityOyunEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun cevap(view: View){

        binding.button1.text = "Cevap"

        var sonuc = binding.cevap.text.toString()
        if(sonuc==kelime){

            score+=puan
            binding.score.text="Score: " + score
            binding.cevap.text = null
            start()
        }
        else{
            score-=puan
            binding.score.text="Score: " + score
            binding.cevap.text = null
            start()
        }


    }
    fun harfAlim(view: View){


        if(sayi>=kelime.length){
            sayi--
        }

        if(sayi==kelime.length-1){
            binding.button1.text="Devam"
        }

        if(cizgi[sayi].toString()=="- "){
            cizgi[sayi]=kelime[sayi].toString()
            a=0
            binding.soru.text=""


            for(i in 0..kelime.length-1){

                binding.soru.append(cizgi[a])
                a++
            }

            sayi++
            puan-=100
            binding.puan.text = puan.toString()
        }

    }
}