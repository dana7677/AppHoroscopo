package com.example.horoscopo_app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo_app.R
import com.example.horoscopo_app.adapters.HoroscopoAdapter
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.data.SimboloZodiacoProvider
import com.example.horoscopo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

lateinit var simboloZodiacoList: List<SimboloZodiaco>

lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Forma de Rellenar el Value sin bindeo con el Recycler ya creado

        /*
        val adapter = HoroscopoAdapter(simboloZodiacoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // */

        recyclerView = findViewById(R.id.recyclerHoroscope)
        simboloZodiacoList= SimboloZodiacoProvider.findAll()

        binding.recyclerHoroscope.adapter= HoroscopoAdapter(simboloZodiacoList)





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}