package com.example.horoscopo_app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo_app.R
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.data.SimboloZodiacoProvider

class horoscopeSelectedActivity : AppCompatActivity() {

    lateinit var selectedZodiac: SimboloZodiaco
   private lateinit var txtselIconZodiac:TextView
   private lateinit var descriptselZodiac:TextView
   private lateinit var imgselZodiac:ImageView
   private lateinit var buttonBackHoroscope:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horoscope_selected)

        val result=intent.extras?.getString("Horoscopo_Result")

        if(result!=null)
        {
            initComp()
            initUI(result)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun initComp()
    {

        txtselIconZodiac = findViewById(R.id.txtSelIconSimb)
        descriptselZodiac=findViewById<TextView>(R.id.descriptionZodiac)
        imgselZodiac=findViewById<ImageView>(R.id.ImgSelZodiac)
        buttonBackHoroscope=findViewById<Button>(R.id.BackToHoroscopo)

        buttonBackHoroscope.setOnClickListener { finish() }



    }
    private fun initUI(id:String)
    {
        selectedZodiac= SimboloZodiacoProvider.findById(id)
        supportActionBar?.title = getString(selectedZodiac.Nombre)
        txtselIconZodiac.setText(selectedZodiac.Nombre)
        imgselZodiac.setImageResource(selectedZodiac.Icono)


    }
}