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

                    SimboloZodiaco("Aries","direccion imagen"),
                    SimboloZodiaco("Taurus","direccion imagen"),
                    SimboloZodiaco("Geminis","direccion imagen"),
                    SimboloZodiaco("Cancer","direccion imagen"),
                    SimboloZodiaco("Leo","direccion imagen"),
                    SimboloZodiaco("Virgo","direccion imagen"),
                    SimboloZodiaco("Libra","direccion imagen"),
                    SimboloZodiaco("Scorpio","direccion imagen"),
                    SimboloZodiaco("Sagittarius","direccion imagen"),
                    SimboloZodiaco("Capricorn","direccion imagen"),
                    SimboloZodiaco("Aquarius","direccion imagen"),
                    SimboloZodiaco("Pisces","direccion imagen"),
                 )
        )




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}