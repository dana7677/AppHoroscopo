package com.example.horoscopo_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo_app.databinding.ViewSimbolozodiacoItemBinding


class HoroscopoAdapter(private val simboloZodiaco:List<SimboloZodiaco>):
    RecyclerView.Adapter<HoroscopoAdapter.ViewHolder>() {

    //Componenente dentro del ViewHolder que va a componer la vista
    class ViewHolder(binding:ViewSimbolozodiacoItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(simboloZodiaco:SimboloZodiaco)
        {
        }
    }

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

}