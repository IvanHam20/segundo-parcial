package edu.iest.segundo_parcial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*

class FormActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etNombreGato: EditText
    private lateinit var etEdadGato: EditText
    private val NOMBRE_KEY = "nombre"
    private val NOMBREGATO_KEY = "nombre_gato"
    private val EDADGATO_KEY = "edad_gato"
    private var nombre: String = ""
    private var nombreGato: String = ""
    private var edadGato: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        inicializarVistas()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.run {
            putString(NOMBRE_KEY, nombre)
            putString(NOMBREGATO_KEY, nombreGato)
            putInt(EDADGATO_KEY, edadGato)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("PREFERENCIAS", "onRestoreInstanceState")
        nombre = savedInstanceState.getString(NOMBRE_KEY).toString()
        nombreGato = savedInstanceState.getString(NOMBREGATO_KEY).toString()
        edadGato = savedInstanceState.getInt(EDADGATO_KEY)
        etNombre.setText(nombre)
        etNombreGato.setText(nombreGato)
        etEdadGato.setText(edadGato.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("PREFERENCIAS", "onResume")
        if (TextUtils.isEmpty(nombre)) {
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombre = miSharedPreferences.getString(NOMBRE_KEY, "").toString()
            nombreGato = miSharedPreferences.getString(NOMBREGATO_KEY, "").toString()
            edadGato = miSharedPreferences.getInt(EDADGATO_KEY, 0)
        }

        etNombre.setText(nombre)
        etNombreGato.setText(nombreGato)
        etEdadGato.setText(edadGato.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.d("PREFERENCIAS", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PREFERENCIAS", "onPause")
        val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
        val editor = miSharedPreferences.edit()
        editor.putString(NOMBRE_KEY, nombre)
        editor.putString(NOMBREGATO_KEY, nombreGato)
        editor.putInt(EDADGATO_KEY, edadGato)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PREFERENCIAS", "onDestroy")
    }

    private fun inicializarVistas() {
        etNombre = findViewById(R.id.etNombre)
        etNombreGato = findViewById(R.id.etNombreGato)
        etEdadGato = findViewById(R.id.etEdadGato)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            try {
                nombre = etNombre.text.toString()
                nombreGato = etNombreGato.text.toString()
                edadGato = etEdadGato.text.toString().toInt()
                Toast.makeText(this, "Tus datos se guardaron correctamente", Toast.LENGTH_LONG).show()

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
            }
        }
    }
}