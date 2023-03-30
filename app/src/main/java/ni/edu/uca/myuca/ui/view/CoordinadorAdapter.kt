package ni.edu.uca.myuca.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.myuca.data.model.Coordinador
import ni.edu.uca.myuca.databinding.CoordinadoresItemsBinding

class CoordinadorAdapter :
    ListAdapter<Coordinador, CoordinadorAdapter.CoordViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordViewHolder {
        val binding = CoordinadoresItemsBinding.inflate(
            LayoutInflater.from(parent.context)
        )
        return CoordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class CoordViewHolder(private val binding: CoordinadoresItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coordinador: Coordinador) {
            binding.apply {
                nombrestv.text = coordinador.nombres
                apellidostv.text = coordinador.apellidos
                fechaNactv.text = parseFechaNac(coordinador.fechaNac)
                titulotv.text = coordinador.titulo
                facultadtv.text = coordinador.facultad
                emailtv.text = coordinador.email
            }
        }


        private fun parseFechaNac(fechaNac: String): String {
            return fechaNac
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Coordinador>() {
            override fun areItemsTheSame(oldItem: Coordinador, newItem: Coordinador): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Coordinador, newItem: Coordinador): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }
}