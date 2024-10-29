package com.example.horoscopo_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.databinding.ViewSimbolozodiacoItemBinding


class HoroscopoAdapter(private val simboloZodiaco:List<SimboloZodiaco>):
    RecyclerView.Adapter<HoroscopoAdapter.ViewHolder>() {

    //Componenente dentro del ViewHolder que va a componer la vista


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //De Context coge a la vista de su padre que lo ha creado.


                val binding=ViewSimbolozodiacoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    //Devolver automaticamente la cantidad de simbolos en nuestro Lista del zodiaco
    override fun getItemCount()=simboloZodiaco.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(simboloZodiaco[position])
    }

    class ViewHolder(private val binding:ViewSimbolozodiacoItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(simboloZodiaco: SimboloZodiaco)
        {
            val context = itemView.context
            binding.txtIconSimb.setText(simboloZodiaco.Nombre)
            binding.ImgZodiac.setImageResource(simboloZodiaco.Icono)
            binding.txtFechasZodiac.setText(simboloZodiaco.Dates)
            //Este otro caso necesita la vista que estamos utilizando
            //Ademas necesita resolver la llamada de El Get drawable.
            //binding.ImgZodiac.setImageDrawable(context.getDrawable(simboloZodiaco.Icono))


        }
    }
}