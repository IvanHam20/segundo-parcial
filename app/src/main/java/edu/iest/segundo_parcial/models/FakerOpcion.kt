package edu.iest.segundo_parcial.models

import edu.iest.segundo_parcial.R
import edu.iest.segundo_parcial.models.Opcion

class FakerOpcion {

    fun getOpciones() : ArrayList<Opcion>{
        val opciones : ArrayList<Opcion> = arrayListOf<Opcion>()

        val opcion = Opcion(1,"Gatos", R.drawable.cat)
        opciones.add(opcion)//1
        opciones.add(Opcion(2,"Profile", R.drawable.profile))
        opciones.add(Opcion(3,"Config", R.drawable.config))
        opciones.add(Opcion(4,"Close", R.drawable.close))

        return opciones
    }
}