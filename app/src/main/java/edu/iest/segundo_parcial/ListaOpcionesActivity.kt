package edu.iest.segundo_parcial

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.iest.segundo_parcial.adapters.OpcionAdapter
import edu.iest.segundo_parcial.models.FakerOpcion

class ListaOpcionesActivity : AppCompatActivity() {

    private var cantidadColumnas = 2
    private var orientacion = GridLayoutManager.VERTICAL
    private lateinit var administradorDeLayouts: GridLayoutManager
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_opciones)

        val opciones = FakerOpcion().getOpciones()
        recycler = findViewById<RecyclerView>(R.id.recyclerOpciones)

        administradorDeLayouts = GridLayoutManager(this, cantidadColumnas, orientacion, false)
        recycler.layoutManager = administradorDeLayouts
        recycler.adapter = OpcionAdapter(opciones, this)
    }

    private fun actualizarLayout() {
        administradorDeLayouts = GridLayoutManager(this, cantidadColumnas, orientacion, false)
        recycler.layoutManager = administradorDeLayouts
    }

    fun connectToInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (activeNetwork != null && activeNetwork.isConnected) {
            // Hay conexión a internet
            Toast.makeText(this, "Hay conexión a internet", Toast.LENGTH_SHORT).show()
        } else {
            // No hay conexión a internet
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_SHORT).show()
        }
        return isConnected
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.optionMenu -> {
                connectToInternet()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}