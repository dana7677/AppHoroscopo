package com.example.horoscopo_app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo_app.R
import com.example.horoscopo_app.adapters.HoroscopoAdapter
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.data.SimboloZodiacoProvider
import com.example.horoscopo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

lateinit var simboloZodiacoList: List<SimboloZodiaco>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        simboloZodiacoList= SimboloZodiacoProvider.findAll()

        binding.recyclerHoroscope.adapter= HoroscopoAdapter(
            listOf(
                SimboloZodiaco("Aries", R.string.horoscope_name_aries,R.drawable.aries, R.string.horoscope_date_aries),
                SimboloZodiaco("Taurus", R.string.horoscope_name_taurus,R.drawable.taurus, R.string.horoscope_date_taurus),
                SimboloZodiaco("Geminis", R.string.horoscope_name_gemini,R.drawable.gemini, R.string.horoscope_date_gemini),
                SimboloZodiaco("Cancer", R.string.horoscope_name_cancer,R.drawable.cancer, R.string.horoscope_date_cancer),
                SimboloZodiaco("Leo", R.string.horoscope_name_leo,R.drawable.leo, R.string.horoscope_date_leo),
                SimboloZodiaco("Virgo", R.string.horoscope_name_virgo,R.drawable.virgo, R.string.horoscope_date_virgo),
                SimboloZodiaco("Libra", R.string.horoscope_name_libra,R.drawable.libra, R.string.horoscope_date_libra),
                SimboloZodiaco("Scorpio", R.string.horoscope_name_scorpio,R.drawable.scorpio, R.string.horoscope_date_scorpio),
                SimboloZodiaco("Sagitarius", R.string.horoscope_name_sagittarius,R.drawable.sagitarius, R.string.horoscope_date_sagittarius),
                SimboloZodiaco("Capricorn", R.string.horoscope_name_capricorn,R.drawable.capricorn, R.string.horoscope_date_capricorn),
                SimboloZodiaco("Aquarius", R.string.horoscope_name_aquarius,R.drawable.aquarius, R.string.horoscope_date_aquarius),
                SimboloZodiaco("Pisces", R.string.horoscope_name_pisces,R.drawable.pisces, R.string.horoscope_date_pisces),

            )
        )




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}