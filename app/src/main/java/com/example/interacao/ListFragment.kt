package com.example.interacao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interacao.adapter.TarefaAdapter
import com.example.interacao.adapter.TaskItemClickListener
import com.example.interacao.databinding.FragmentListBinding
import com.example.interacao.model.Tarefas
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListFragment : Fragment(), TaskItemClickListener {

    val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = TarefaAdapter(this, mainViewModel)
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)


        mainViewModel.listTarefas()
        lifecycleScope.launch {
            mainViewModel.myQueryResponse.collect {
                    response -> adapter.setData(response)
            }
        }

        binding.floatingActionButton.setOnClickListener {
            mainViewModel.tarefaSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }
        binding.imageButtonVoltar.setOnClickListener(){
            findNavController().navigate(R.id.action_listFragment_to_menuTela)
        }

        return binding.root
    }

    override fun onTaskClicked(tarefas: Tarefas) {
        mainViewModel.tarefaSelecionada = tarefas
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}