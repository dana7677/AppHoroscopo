package com.example.horoscopo_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo_app.Horoscopo_APP_Application.Companion.prefs
import com.example.horoscopo_app.R
import com.example.horoscopo_app.data.SimboloZodiaco
import com.example.horoscopo_app.databinding.ViewSimbolozodiacoItemBinding


class HoroscopoAdapter(private var simboloZodiaco:List<SimboloZodiaco>,private val onItemClickListener: (Int) -> Unit):
    RecyclerView.Adapter<HoroscopoAdapter.ViewHolder>() {

        //SearchViewConfig

       fun setFilteredList(filterList:List<SimboloZodiaco> )

       {
           this.simboloZodiaco=filterList
            notifyDataSetChanged()

       }

    //Componenente dentro del ViewHolder que va a componer la vista


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //De Context coge a la vista de su padre que lo ha creado.


        val binding=ViewSimbolozodiacoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    //Devolver automaticamente la cantidad de simbolos en nuestro Lista del zodiaco
    override fun getItemCount()=simboloZodiaco.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.bind(simboloZodiaco[position])

        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
    }

    companion object
    {
        const val Horoscopo_KEY="Horoscopo_Result"
    }
    class ViewHolder(private val binding:ViewSimbolozodiacoItemBinding):RecyclerView.ViewHolder(binding.root)
    {

        fun bind(simboloZodiaco: SimboloZodiaco)
        {

            val context = itemView.context
            binding.txtIconSimb.setText(simboloZodiaco.Nombre)
            binding.ImgZodiac.setImageResource(simboloZodiaco.Icono)
            binding.txtFechasZodiac.setText(simboloZodiaco.Dates)
            if(prefs.getName()==simboloZodiaco.id)
            {

                //binding.linearBackground.setBackgroundColor(BackgroundCompFaved)
                var color = R.color.BackgroundCompFaved

                //binding.linearBackground.background=R.color.BackgroundCompFaved.toDrawable()

                binding.linearBackground.setBackgroundColor(context.getColor(R.color.BackgroundCompFaved))


            }



            //Antiguo metodo pero mala Praxis, mejor hacerlo a traves de una función lambda
            /*
            itemView.setOnClickListener {


                val intent = Intent(context,horoscopeSelectedActivity::class.java)
                intent.putExtra(Horoscopo_KEY,simboloZodiaco.id)
                context.startActivity(intent)

            }
            */


            //Este otro caso necesita la vista que estamos utilizando
            //Ademas necesita resolver la llamada de El Get drawable.
            //binding.ImgZodiac.setImageDrawable(context.getDrawable(simboloZodiaco.Icono))


        }
    }
}