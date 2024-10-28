package com.example.horoscopo_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerHoroscope.adapter=HoroscopoAdapter(
            listOf(

                    SimboloZodiaco(R.string.Aries.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Taurus.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Geminis.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Cancer.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Leo.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Virgo.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Libra.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Scorpio.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Sagittarius.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Capricorn.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Aquarius.toString(),"direccion imagen"),
                    SimboloZodiaco(R.string.Pisces.toString(),"direccion imagen"),
                 )
        )




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}