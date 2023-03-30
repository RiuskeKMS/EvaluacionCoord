package ni.edu.uca.myuca.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.myuca.databinding.FragmentCoordBinding

class CoordinadorFragment : Fragment() {


    private val viewModelFY: ViewModelFY by activityViewModels {
        ViewModelFY.factory
    }

    private var _binding: FragmentCoordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCoordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = CoordinadorAdapter()
        val layoutManager = LinearLayoutManager(this.context)

        binding.rcvListaCoords.layoutManager = (layoutManager)
        binding.rcvListaCoords.adapter = adapter

        this.context?.let { context ->
            viewModelFY.conseguirDatos(context) {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}