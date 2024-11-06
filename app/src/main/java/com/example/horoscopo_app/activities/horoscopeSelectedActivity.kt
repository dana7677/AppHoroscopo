package com.example.horoscopo_app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.horoscopo_app.Horoscopo_APP_Application.Companion.prefs
import com.example.horoscopo_app.R
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.data.SimboloZodiacoProvider
import com.example.horoscopo_app.dataRetrofit.RetrofitServiceFactory
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.launch
import java.util.Locale

enum class Language(val code: String) {
    ENGLISH("en"),
    GERMAN("de"),
    SPANISH("es"),
    FRENCH("fr");
}
class horoscopeSelectedActivity : AppCompatActivity() {

    lateinit var selectedZodiac: SimboloZodiaco
   private lateinit var txtselIconZodiac:TextView
   private lateinit var descriptselZodiac:TextView
   private lateinit var imgselZodiac:ImageView
   private lateinit var buttonBackHoroscope:Button
   private lateinit var options:TranslatorOptions
   private lateinit var oriLanguageForDestLanguage:Translator
   private lateinit var conditions:DownloadConditions
   private lateinit var txtTraducted:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horoscope_selected)

        options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        oriLanguageForDestLanguage = Translation.getClient(options)


        //Traducccion

         conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()





        val result = intent.extras?.getString("Horoscopo_Result")

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


        val service= RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            val value = service.getDailyZodiac(selectedZodiac.id,"TODAY")

            selectIdioma()

            //Traduccion del texto al idioma establecido
            oriLanguageForDestLanguage.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    // Model downloaded successfully. Okay to start translating.
                    // (Set a flag, unhide the translation UI, etc.)

                    oriLanguageForDestLanguage.translate(value.data.horoscope_data)
                        .addOnSuccessListener { translatedText ->
                            // Translation successful.
                            PutTheDescriptionText(translatedText)
                        }
                        .addOnFailureListener { exception ->
                            // Error.
                            // ...
                        }
                }
                .addOnFailureListener { exception ->
                    // Model couldn’t be downloaded or other internal error.
                    // ...
                }
            //descriptselZodiac.setText(value.data.horoscope_data)

        }

        //Hacer en la parte del menu el cambio del nombre y añadir un subtitulo de fechas
        supportActionBar?.title = getString(selectedZodiac.Nombre)
        supportActionBar?.subtitle=getString(selectedZodiac.Dates)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txtselIconZodiac.setText(selectedZodiac.Nombre)
        imgselZodiac.setImageResource(selectedZodiac.Icono)


    }
    private fun selectIdioma()
    {
        val currentLanguage = Locale.getDefault().language // Obtén el código del idioma actual

        when (currentLanguage) {
            "en" -> {
                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.ENGLISH)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)
            }
            "es" -> {
                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.SPANISH)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)
            }
            "ar" -> {
                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.ARABIC)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)
            }
            "fr" -> {
                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.FRENCH)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)
            }
            "zh" -> {

                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.CHINESE)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)
            }
            else -> {
                options = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.CHINESE)
                    .build()
                oriLanguageForDestLanguage = Translation.getClient(options)

                println("Idioma no soportado: $currentLanguage")
            }
        }
    }
    private fun PutTheDescriptionText(txtTraducido:String)
    {

        txtTraducted=txtTraducido
        descriptselZodiac.setText(txtTraducido)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_two,menu)

        if(menu!=null)
        {
            val favIcon = menu.findItem(R.id.fav)
            if(prefs.getName()==selectedZodiac.id)
            {
                if(prefs.getFav()==true)
                {
                    favIcon.setIcon(R.drawable.faved_icon)
                }
                else
                {
                    favIcon.setIcon(R.drawable.fav_icon)
                }
            }
            else
            {
                favIcon.setIcon(R.drawable.fav_icon)
            }

        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            android.R.id.home->
                {
                    finish()
                    return true
                }
            R.id.fav->
                {
                    if(prefs.getName()==selectedZodiac.id)
                    {
                        if(prefs.getFav()==true)
                        {
                            clearSharedPrefs()
                            item.setIcon(R.drawable.fav_icon)
                        }
                        else
                        {
                            saveFavZodiac()
                            item.setIcon(R.drawable.faved_icon)
                        }
                    }
                    else
                    {

                        clearSharedPrefs()
                       saveFavZodiac()
                        item.setIcon(R.drawable.faved_icon)

                    }

                    return true
                }
            R.id.share->
                {
                    shareDats(selectedZodiac.id)
                    return true
                }
            else->
            {
                return super.onOptionsItemSelected(item)
            }
        }
    }


    private fun shareDats(zodiacShare:String)
    {
        val intent=Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,zodiacShare)
            type="text/plain"
            putExtra(Intent.EXTRA_TITLE,txtTraducted)
        }
        val shareIntent=Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
    private fun clearSharedPrefs()
    {
        prefs.wipe()
    }
    private fun saveFavZodiac()
    {
        prefs.saveFav(true)
        prefs.saveZodiacName(selectedZodiac.id)
    }

    override fun onBackPressed() {


        super.onBackPressed()
    }
}