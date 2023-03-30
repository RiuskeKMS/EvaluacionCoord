package ni.edu.uca.myuca.ui.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ni.edu.uca.myuca.data.model.Coordinador
import org.json.JSONException
import org.json.JSONObject

class ViewModelFY() : ViewModel() {

    val url = "http://192.168.1.18:8080/Pruebabd/coordinador/show.php"

    fun conseguirDatos(context: Context, listSubmitter: (ArrayList<Coordinador>) -> Unit) {

        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                listSubmitter(parseJsonObj(response))
            }
        ) { error ->
            println("ups...")
        }
        queue.add(stringRequest)
    }

    private fun parseJsonObj(jsonRespose: String): ArrayList<Coordinador> {
        var coordinadores =  ArrayList<Coordinador>()

        try {
            val coordinadorObj = JSONObject(jsonRespose).getJSONArray("data")

            for (i in 0 until  coordinadorObj.length()) {
                val jsonObj =  coordinadorObj.getJSONObject(i)

                val idC = jsonObj.getInt("idC")
                val nombres = jsonObj.getString("nombres")
                val apellidos = jsonObj.getString("apellidos")
                val fechaNac = jsonObj.getString("fechaNac")
                val titulo = jsonObj.getString("titulo")
                val email = jsonObj.getString("email")
                val facultad = jsonObj.getString("facultad")

                var coordinador = Coordinador(
                    idC, nombres, apellidos,
                    fechaNac, titulo, email, facultad
                )

                if (!coordinador.titulo.contains("msc", true)){
                    coordinadores.add(coordinador)
                }

            }
            return coordinadores
        } catch (e: JSONException) {
            println("hay un error chavalo: ${e.message}")
        }

        return coordinadores
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            addInitializer(ViewModelFY::class) {
                ViewModelFY()
            }
            build()
        }
    }
}