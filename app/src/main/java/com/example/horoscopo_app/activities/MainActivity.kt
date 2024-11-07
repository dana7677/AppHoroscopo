package com.example.horoscopo_app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo_app.R
import com.example.horoscopo_app.adapters.HoroscopoAdapter
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.data.SimboloZodiacoProvider
import com.example.horoscopo_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

lateinit var simboloZodiacoList: List<SimboloZodiaco>
lateinit var recyclerView: RecyclerView
private lateinit var Adapter:HoroscopoAdapter
lateinit var searchViewMenu:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerHoroscope)
        simboloZodiacoList= SimboloZodiacoProvider.findAll()

        //Menu de busqueda
        Adapter =HoroscopoAdapter(simboloZodiacoList){ position ->
            navigateToDetail(simboloZodiacoList[position])
        }

        binding.recyclerHoroscope.adapter = Adapter


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

    }


    //Empleamos este metodo porque se ejecuta la volver a la vista aunque ya este creado
    override fun onResume() {
        super.onResume()
        Adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu,menu)

        if(menu!=null)
        {

            val searchItem = menu.findItem(R.id.searcher)

            searchViewMenu = searchItem.actionView as SearchView

            searchViewMenu.clearFocus()

            searchViewMenu.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    return false

                }

                override fun onQueryTextChange(newText: String): Boolean {
                    filterList(newText)
                    return true

                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun filterList(newText: String) {

        //Lista Filtrada de posibles opciones.

        val filteredList = simboloZodiacoList.filter {
            getString(it.Nombre).contains(newText, true)
            || getString(it.Dates).contains(newText, true)
        }

        Adapter.setFilteredList(filteredList)
        if(filteredList.isEmpty())
        {
            Toast.makeText(this,"No data Found",Toast.LENGTH_SHORT).show()
        }

    }

    fun navigateToDetail(simboloZodiaco: SimboloZodiaco) {
        val intent: Intent = Intent(this, horoscopeSelectedActivity::class.java)
        intent.putExtra(HoroscopoAdapter.Horoscopo_KEY,simboloZodiaco.id)
        startActivity(intent)
    }
}