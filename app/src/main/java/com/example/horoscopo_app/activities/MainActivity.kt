package com.example.horoscopo_app.activities

import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
private lateinit var arrayAdapter:ArrayAdapter<SimboloZodiaco>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        recyclerView = findViewById(R.id.recyclerHoroscope)
        simboloZodiacoList= SimboloZodiacoProvider.findAll()


        //Forma de Rellenar el Value sin bindeo con el Recycler ya creado
        /*
        val adapter = HoroscopoAdapter(simboloZodiacoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
*/

        //Menu de busqueda
        arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,simboloZodiacoList)

       itemAdapter =HoroscopoAdapter(simboloZodiacoList)

        binding.recyclerHoroscope.adapter = HoroscopoAdapter(simboloZodiacoList)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu,menu)

        val buscar= menu?.findItem(R.id.searcher)
        val searchView = buscar?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let{
                    Toast.makeText(this@MainActivity,"Quieres Buscar Coincidencias con: ${query}:",Toast.LENGTH_SHORT).show()
                    this@MainActivity.recyclerView.adapter
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)

                return false
            }


            
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun filterList(newText: String?) {

        List<SimboloZodiaco> filteredList =

        for(SimboloZodiaco simboloZodiaco:SimboloZodiacoList)
        {

        }


    }
}