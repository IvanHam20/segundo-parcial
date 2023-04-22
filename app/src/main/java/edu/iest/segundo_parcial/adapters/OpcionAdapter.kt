package edu.iest.segundo_parcial.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.iest.segundo_parcial.FormActivity
import edu.iest.segundo_parcial.MainActivity
import edu.iest.segundo_parcial.R
import edu.iest.segundo_parcial.models.Opcion

class OpcionAdapter(opciones: ArrayList<Opcion>, context: Context) : RecyclerView.Adapter<OpcionAdapter.ContenedorDeVista>() {

    var inner_opciones: ArrayList<Opcion> = opciones
    var inner_context: Context = context

    inner class ContenedorDeVista(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        //aqui haremos el inflate del layout
        val tvOpcion: TextView
        val ivFoto: ImageView

        init {
            //Define click listener for the ViewHolder's View.
            tvOpcion = view.findViewById(R.id.tvOpcion)
            ivFoto = view.findViewById(R.id.ivFoto)
            ivFoto.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (adapterPosition >= 0) {
                //Necesario usar contexto
                val miSharedPreferences = inner_context.getSharedPreferences("PERSISTENCIA",
                    AppCompatActivity.MODE_PRIVATE
                )

                val opcion: Opcion = inner_opciones.get(adapterPosition)
                if (opcion.id == 2) {
                    val i = Intent(inner_context, FormActivity::class.java)
                    inner_context.startActivity(i)
                } else if (opcion.id == 4) {
                    System.exit(0);
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return ContenedorDeVista(view)
    }

    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val opcion: Opcion = inner_opciones.get(position)
        holder.tvOpcion.text = opcion.nombre
        holder.ivFoto.setImageResource(opcion.imagen)
    }

    override fun getItemCount(): Int {
        return inner_opciones.size
    }
}
